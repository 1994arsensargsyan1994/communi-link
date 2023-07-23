package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.facade.creation.ReservationCreationHandler;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultReservationServiceFacade implements ReservationServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultReservationServiceFacade.class);

    private final ReservationCreationHandler reservationCreationHandler;

    DefaultReservationServiceFacade(final ReservationCreationHandler reservationCreationHandler) {
        this.reservationCreationHandler = reservationCreationHandler;
    }

    @Override
    public ReservationCreationResponse reserve(final Long communityId, final ReservationCreationRequest request) {
        Assert.notNull(communityId, "Null was passed as an argument for parameter 'communityId'.");
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Reservation. communityId: {} , Request: {}.", communityId, request);
        return reservationCreationHandler.reserve(communityId, request);
    }
}
