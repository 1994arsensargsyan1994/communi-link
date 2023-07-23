package com.arsensargsyan.communi.link.api.resource.community.reservation;

import com.arsensargsyan.communi.link.api.facade.ReservationServiceFacade;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
class DefaultReservationController implements ReservationController {

    private final ReservationServiceFacade reservationServiceFacade;

    public DefaultReservationController(final ReservationServiceFacade reservationServiceFacade) {
        this.reservationServiceFacade = reservationServiceFacade;
    }

    @Override
    public ResponseEntity<ReservationCreationResponse> reserve(final Long communityId, final ReservationCreationRequest request) {
        return ResponseEntity.ok(reservationServiceFacade.reserve(communityId, request));
    }
}