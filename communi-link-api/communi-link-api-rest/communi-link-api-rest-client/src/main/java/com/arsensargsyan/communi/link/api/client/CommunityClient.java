package com.arsensargsyan.communi.link.api.client;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
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

    @PostMapping(
            value = "/community/{communityId}/reserve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ReservationCreationResponse reserve(
            @PathVariable("communityId") Long communityId, @NotNull @Valid @RequestBody ReservationCreationRequest request
    );
}