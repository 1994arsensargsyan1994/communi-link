package com.arsensargsyan.communi.link.api.client;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "communityClient", url = "${community.service.url:http://localhost:8089}")
public interface CommunityClient {

    @PostMapping(value = "/community",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CommunityCreationResponse create(@NotNull @Valid @RequestBody CommunityCreationRequest request);

    @GetMapping(path = "/community/{id}/details",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    LookupCommunityDetailsResponse lookupDetails(@PathVariable("id") @NotNull @Positive Long id);

    @PostMapping(
            value = "/community/{communityId}/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ReservationCreationResponse reserve(
            @PathVariable("communityId") Long communityId, @NotNull @Valid @RequestBody ReservationCreationRequest request
    );

    @DeleteMapping(
            value = "/community/{communityId}/cancel/{reservationId}"
    )
    ReservationCancelResponse cancel(
            @PathVariable("communityId") Long communityId, @PathVariable("reservationId") Long reservationId
    );
}