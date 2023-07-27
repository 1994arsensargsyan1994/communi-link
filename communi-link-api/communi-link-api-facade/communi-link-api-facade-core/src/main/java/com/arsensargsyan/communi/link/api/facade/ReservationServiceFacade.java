package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;

public interface ReservationServiceFacade {

    ReservationCreationResponse reserve(Long communityId, ReservationCreationRequest request);

    ReservationCancelResponse cancel(Long communityId, Long reservationId);
}
