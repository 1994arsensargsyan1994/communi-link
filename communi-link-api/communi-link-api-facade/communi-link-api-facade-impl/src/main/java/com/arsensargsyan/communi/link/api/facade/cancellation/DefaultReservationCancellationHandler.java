package com.arsensargsyan.communi.link.api.facade.cancellation;

import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.service.ReservationService;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationParameter;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultReservationCancellationHandler implements ReservationCancellationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultReservationCancellationHandler.class);

    private final ReservationService reservationService;

    DefaultReservationCancellationHandler(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public ReservationCancelResponse cancel(final Long communityId, final Long reservationId) {
        final ReservationCancelResponse response = new ReservationCancelResponse();

        final ReservationCancellationResult result = reservationService.cancel(
                new ReservationCancellationParameter(
                        communityId, reservationId
                )
        );

        if (result.hasFailures()) {
            logger.warn("Cancel Reservation failed with: {}.", result.failures());
            response.setResultFailures(result.failures());
            return response;
        }
        return new ReservationCancelResponse();
    }
}