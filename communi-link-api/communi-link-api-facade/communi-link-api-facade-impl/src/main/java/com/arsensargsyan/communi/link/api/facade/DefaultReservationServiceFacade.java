package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.facade.cancellation.ReservationCancellationHandler;
import com.arsensargsyan.communi.link.api.facade.creation.ReservationCreationHandler;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultReservationServiceFacade implements ReservationServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultReservationServiceFacade.class);

    private final ReservationCreationHandler reservationCreationHandler;

    private final ReservationCancellationHandler reservationCancellationHandler;

    DefaultReservationServiceFacade(
            final ReservationCreationHandler reservationCreationHandler,
            final ReservationCancellationHandler reservationCancellationHandler
    ) {
        this.reservationCreationHandler = reservationCreationHandler;
        this.reservationCancellationHandler = reservationCancellationHandler;
    }

    @Override
    public ReservationCreationResponse reserve(final Long communityId, final ReservationCreationRequest request) {
        Assert.notNull(communityId, "Null was passed as an argument for parameter 'communityId'.");
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Reservation. communityId: {} , Request: {}.", communityId, request);
        return reservationCreationHandler.reserve(communityId, request);
    }

    @Override
    public ReservationCancelResponse cancel(final Long communityId, final Long reservationId) {
        Assert.notNull(communityId, "Null was passed as an argument for parameter 'communityId'.");
        Assert.notNull(reservationId, "Null was passed as an argument for parameter 'reservationId'.");
        return reservationCancellationHandler.cancel(communityId, reservationId);
    }
}
