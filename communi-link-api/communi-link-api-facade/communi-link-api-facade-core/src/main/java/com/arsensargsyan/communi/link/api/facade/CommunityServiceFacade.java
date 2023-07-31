package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;

public interface CommunityServiceFacade {

    CommunityCreationResponse create(CommunityCreationRequest request);

    LookupCommunityDetailsResponse lookupDetails(Long id);
}
