package com.arsensargsyan.communi.link.service;

import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;
import com.arsensargsyan.communi.link.service.lookup.LookupCommunityDetailsResult;

public interface CommunityService {

    CommunityCreationResult create(CommunityCreationParameter parameter);

    LookupCommunityDetailsResult lookupDetails(Long id);
}