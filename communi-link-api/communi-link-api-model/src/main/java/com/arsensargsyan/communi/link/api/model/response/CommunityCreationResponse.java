package com.arsensargsyan.communi.link.api.model.response;

import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommunityCreationResponse extends FailureAwareResponse {

    @JsonProperty("communityId")
    private Long communityId;

    public CommunityCreationResponse() {
        super();
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(final Long communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("communityId", getCommunityId())
                .toString();
    }
}