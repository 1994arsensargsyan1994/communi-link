package com.arsensargsyan.communi.link.api.facade.lookup;

import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;

public interface CommunityLookupHandler {

    LookupCommunityDetailsResponse lookupDetails(Long id);
}