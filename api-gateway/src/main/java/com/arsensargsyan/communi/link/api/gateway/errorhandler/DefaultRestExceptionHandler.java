package com.arsensargsyan.communi.link.api.gateway.errorhandler;

import java.util.List;

import com.arsensargsyan.communi.link.common.FailureDto;
import com.arsensargsyan.communi.link.common.api.model.CommonFailures;
import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultRestExceptionHandler extends ResponseEntityExceptionHandler implements RestExceptionHandler {

    private static final Logger default_logger = LoggerFactory.getLogger(DefaultRestExceptionHandler.class);

    private static final FailureAwareResponse FAILURE_AWARE_RESPONSE = new FailureAwareResponse();

    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull final HttpMessageNotReadableException ex,
                                                                  @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatusCode status,
                                                                  @NonNull final WebRequest request) {
        default_logger.debug("Http message not readable.", ex);
        FAILURE_AWARE_RESPONSE.setFailures(getFailures(ex));
        return ResponseEntity.badRequest().body(FAILURE_AWARE_RESPONSE);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException ex,
                                                                  @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatusCode status,
                                                                  @NonNull final WebRequest request) {
        default_logger.debug("Method argument not valid.", ex);
        FAILURE_AWARE_RESPONSE.setFailures(getFailures(ex));
        return ResponseEntity.badRequest().body(FAILURE_AWARE_RESPONSE);
    }

    @Override
    public ResponseEntity<FailureAwareResponse> onException(final Exception ex) {
        default_logger.error("Exception was thrown when processing the request.", ex);
        FAILURE_AWARE_RESPONSE.setFailures(internalServerError());
        return ResponseEntity.internalServerError().body(FAILURE_AWARE_RESPONSE);
    }

    @Override
    public ResponseEntity<FailureAwareResponse> constraintViolationFailed(final ConstraintViolationException ex) {
        default_logger.debug("Constraint violation failed.", ex);
        FAILURE_AWARE_RESPONSE.setFailures(getFailures(ex));
        return ResponseEntity.badRequest().body(FAILURE_AWARE_RESPONSE);
    }

    private static List<FailureDto> getFailures(final HttpMessageNotReadableException ex) {
        return List.of(new FailureDto("failure.http.message.not.readable", ex.getMessage()));
    }

    private static List<FailureDto> internalServerError() {
        return List.of(new FailureDto(CommonFailures.INTERNAL_SERVER_ERROR.code(), CommonFailures.INTERNAL_SERVER_ERROR.name()));
    }

    private static List<FailureDto> getFailures(final ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(DefaultRestExceptionHandler::failure).toList();
    }

    private static List<FailureDto> getFailures(final MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream()
                .map(DefaultRestExceptionHandler::failure).toList();
    }

    private static FailureDto failure(final ConstraintViolation<?> constraintViolation) {
        return new FailureDto(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }

    private static FailureDto failure(final FieldError fieldError) {
        return new FailureDto(fieldError.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage());
    }
}
