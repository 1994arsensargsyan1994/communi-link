package com.arsensargsyan.communi.link.service.creation;

import com.arsensargsyan.communi.link.common.Failure;
import org.apache.commons.lang3.builder.ToStringBuilder;

public enum CommunityCreationFailure implements Failure {

    COMMUNITY_ALREADY_EXISTS("failure.community.already.exists", "Another Community already exists whit name and type");

    private final String code;

    private final String reason;

    CommunityCreationFailure(final String code, final String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String reason() {
        return reason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code())
                .append("reason", reason())
                .toString();
    }
}