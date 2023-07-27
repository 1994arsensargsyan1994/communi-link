package com.arsensargsyan.communi.link.api.facade.cancellation;

import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;

public interface ReservationCancellationHandler {

    ReservationCancelResponse cancel(final Long communityId, final Long reservationId);
}