package com.arsensargsyan.communi.link.api.facade.creation;

import com.arsensargsyan.communi.link.api.facade.validation.RequestValidator;
import com.arsensargsyan.communi.link.api.facade.validation.ValidationResult;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import com.arsensargsyan.communi.link.common.DataRange;
import com.arsensargsyan.communi.link.service.ReservationService;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationParameter;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultReservationCreationHandler implements ReservationCreationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCommunityCreationHandler.class);

    private final ReservationService reservationService;

    private final RequestValidator<ReservationCreationRequest> requestValidator;

    DefaultReservationCreationHandler(
            final RequestValidator<ReservationCreationRequest> requestValidator,
            final ReservationService reservationService
    ) {
        this.reservationService = reservationService;
        this.requestValidator = requestValidator;
    }

    @Override
    public ReservationCreationResponse reserve(final Long communityId, final ReservationCreationRequest request) {
        final ValidationResult validationResult = requestValidator.validate(request);
        final ReservationCreationResponse response = new ReservationCreationResponse();

        if (validationResult.hasFailures()) {
            logger.warn("Create Reservation validation failed with: {}.", validationResult.failures());
            response.setFailures(validationResult.failures());
            return response;
        }
        final ReservationCreationResult creationResult = reservationService.create(creationParameter(communityId, request));
        if (creationResult.hasFailures()) {
            response.setResultFailures(creationResult.failures());
            return response;
        }
        response.setReservationId(creationResult.reservationId());
        return response;
    }

    private static ReservationCreationParameter creationParameter(final Long communityId, final ReservationCreationRequest request) {
        return new ReservationCreationParameter(
                communityId, request.username(), request.mail(), request.identifier(), new DataRange(request.start(), request.end())
        );
    }
}