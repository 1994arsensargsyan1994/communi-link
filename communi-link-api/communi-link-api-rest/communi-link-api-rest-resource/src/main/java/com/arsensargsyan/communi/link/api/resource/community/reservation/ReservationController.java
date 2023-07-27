package com.arsensargsyan.communi.link.api.resource.community.reservation;

import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        value = "community/{communityId}"
)
public interface ReservationController {

    @PostMapping(path = "/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<ReservationCreationResponse> reserve(
            @PathVariable("communityId") Long communityId, @Valid @NotNull @RequestBody ReservationCreationRequest request
    );

    @DeleteMapping(path = "/cancel/{reservationId}")
    ResponseEntity<ReservationCancelResponse> cancel(
            @PathVariable("communityId") Long communityId, @PathVariable("reservationId") Long reservationId
    );
}