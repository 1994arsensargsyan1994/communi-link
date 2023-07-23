package com.arsensargsyan.communi.link.service.creation;

import com.arsensargsyan.communi.link.common.CommunityType;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record CommunityCreationParameter(String name, CommunityType type, Integer maxCount) {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name())
                .append("type", type())
                .append("maxCount", maxCount())
                .toString();
    }
}