package com.arsensargsyan.communi.link.api.gateway.community;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

import com.arsensargsyan.communi.link.api.client.CommunityClient;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import com.arsensargsyan.communi.link.common.FailureDto;
import com.arsensargsyan.communi.link.common.api.model.CommonFailures;
import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@Validated
@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    private static final long DEFAULT_API_TIMEOUT = 5_000L;

    private final Executor executor;

    private final CommunityClient communityClient;

    public CommunityController(
            @Qualifier("apiGatewayExecutor") final Executor executor,
            final CommunityClient communityClient
    ) {
        this.executor = executor;
        this.communityClient = communityClient;
    }

    @Operation(
            summary = "${community.create.operation.summary}",
            description = "${community.create.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = CommunityCreationResponse.class))
            )
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeferredResult<ResponseEntity<CommunityCreationResponse>> create(
            @Valid @RequestBody final CommunityCreationRequest request
    ) {
        logger.info("Creating community for given request: {}.", request);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> communityClient.create(request), executor),
                CommunityCreationResponse::new
        );
    }

    @Operation(
            summary = "${reserve.create.operation.summary}",
            description = "${reserve.create.operation.description}",
            responses = @ApiResponse(
                    content = @Content(schema = @Schema(oneOf = ReservationCreationResponse.class))
            )
    )
    @PostMapping(
            path = "/{communityId}/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    DeferredResult<ResponseEntity<ReservationCreationResponse>> reserve(
            @PathVariable("communityId") Long communityId, @Valid @NotNull @RequestBody ReservationCreationRequest request
    ) {
        logger.info("Reservation for given request: {}.", request);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> communityClient.reserve(communityId, request), executor),
                ReservationCreationResponse::new
        );
    }

    @DeleteMapping(
            value = "/{communityId}/cancel/{reservationId}"
    )
    DeferredResult<ResponseEntity<ReservationCancelResponse>> cancel(
            @PathVariable("communityId") Long communityId, @PathVariable("reservationId") Long reservationId) {
        logger.info("Reservation for given communityId: {} and reservationId: {}.", communityId, reservationId);
        return deferredResponse(
                CompletableFuture.supplyAsync(() -> communityClient.cancel(communityId, reservationId), executor),
                ReservationCancelResponse::new
        );
    }

    private <R extends FailureAwareResponse> DeferredResult<ResponseEntity<R>> deferredResponse(
            final CompletableFuture<R> responsePromise,
            final Supplier<R> constructor
    ) {
        final DeferredResult<ResponseEntity<R>> deferredResponse = new DeferredResult<>(DEFAULT_API_TIMEOUT);
        responsePromise.whenComplete((response, throwable) -> {
            if (throwable == null) {
                deferredResponse.setResult(responseFor(HttpStatus.OK, response));
            } else {
                logger.error("Request failed.", throwable);
                deferredResponse.setErrorResult(internalServerError(constructor));
            }
        });
        return deferredResponse;
    }

    private static <R extends FailureAwareResponse> ResponseEntity<R> internalServerError(final Supplier<R> constructor) {
        final R response = constructor.get();
        response.setFailures(
                List.of(
                        new FailureDto(
                                CommonFailures.INTERNAL_SERVER_ERROR.code(),
                                CommonFailures.INTERNAL_SERVER_ERROR.reason()
                        )
                )
        );
        return responseFor(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    private static <R extends FailureAwareResponse> ResponseEntity<R> responseFor(final HttpStatus httpStatus, final R body) {
        return ResponseEntity.status(httpStatus).body(body);
    }
}


