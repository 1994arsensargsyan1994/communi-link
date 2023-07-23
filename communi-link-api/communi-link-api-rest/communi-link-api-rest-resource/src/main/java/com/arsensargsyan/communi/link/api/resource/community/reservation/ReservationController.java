package com.arsensargsyan.communi.link.api.resource.community.reservation;

import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        value = "community/{communityId}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public interface ReservationController {

    @PostMapping(path = "/reserve")
    ResponseEntity<ReservationCreationResponse> reserve(
            @PathVariable("communityId") Long communityId, @Valid @NotNull @RequestBody ReservationCreationRequest request
    );
}