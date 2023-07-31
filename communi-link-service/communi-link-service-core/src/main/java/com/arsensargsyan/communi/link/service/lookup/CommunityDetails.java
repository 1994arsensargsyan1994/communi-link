package com.arsensargsyan.communi.link.service.lookup;

import com.arsensargsyan.communi.link.common.CommunityType;

public interface CommunityDetails {

    Long id();

    String name();

    CommunityType type();

    Integer maxCount();

    Integer currentCount();
}