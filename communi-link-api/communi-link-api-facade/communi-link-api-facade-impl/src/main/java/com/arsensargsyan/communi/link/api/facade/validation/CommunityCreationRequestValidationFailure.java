package com.arsensargsyan.communi.link.api.facade.validation;

import org.apache.commons.lang3.builder.ToStringBuilder;

public enum CommunityCreationRequestValidationFailure implements ValidationFailure {

    TYPE_INVALID_VALUE("failure.type.invalid.value", "Type value is invalid.");

    private final String code;

    private final String reason;

    CommunityCreationRequestValidationFailure(final String code, final String reason) {
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