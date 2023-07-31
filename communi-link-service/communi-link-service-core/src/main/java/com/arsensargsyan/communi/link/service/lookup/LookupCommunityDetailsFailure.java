package com.arsensargsyan.communi.link.service.lookup;

import com.arsensargsyan.communi.link.common.Failure;
import org.apache.commons.lang3.builder.ToStringBuilder;

public enum LookupCommunityDetailsFailure implements Failure {

    URM_LIST_NOT_FOUND("failure.urm.list.not.found", "URM list not found.");

    private final String code;

    private final String reason;

    LookupCommunityDetailsFailure(final String code, final String reason) {
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