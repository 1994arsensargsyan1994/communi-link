package com.arsensargsyan.communi.link.api.resource.community;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/community", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CommunityController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommunityCreationResponse> create(@NotNull @Valid @RequestBody CommunityCreationRequest request);

    @GetMapping(path = "/{id}/details")
    ResponseEntity<LookupCommunityDetailsResponse> lookupDetails(@PathVariable("id") @NotNull @Positive Long id);
}