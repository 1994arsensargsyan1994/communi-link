package com.arsensargsyan.communi.link.api.facade.creation;

import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;

public interface ReservationCreationHandler {

    ReservationCreationResponse reserve(Long communityId, ReservationCreationRequest request);
}