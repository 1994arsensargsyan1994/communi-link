package com.arsensargsyan.communi.link.service;

import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;

public interface CommunityService {

    CommunityCreationResult create(CommunityCreationParameter parameter);
}