package com.arsensargsyan.communi.link.api.facade.creation;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;

public interface CommunityCreationHandler {

    CommunityCreationResponse create(final CommunityCreationRequest request);
}