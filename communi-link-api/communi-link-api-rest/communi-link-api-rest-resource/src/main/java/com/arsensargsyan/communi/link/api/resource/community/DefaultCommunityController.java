package com.arsensargsyan.communi.link.api.resource.community;

import com.arsensargsyan.communi.link.api.facade.CommunityServiceFacade;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
class DefaultCommunityController implements CommunityController {

    private final CommunityServiceFacade communityServiceFacade;

    @Autowired
    DefaultCommunityController(final CommunityServiceFacade communityServiceFacade) {
        this.communityServiceFacade = communityServiceFacade;
    }

    @Override
    public ResponseEntity<CommunityCreationResponse> create(final CommunityCreationRequest request) {
        return ResponseEntity.ok(communityServiceFacade.create(request));
    }

    @Override
    public ResponseEntity<LookupCommunityDetailsResponse> lookupDetails(final Long id) {
        return ResponseEntity.ok(communityServiceFacade.lookupDetails(id));
    }
}