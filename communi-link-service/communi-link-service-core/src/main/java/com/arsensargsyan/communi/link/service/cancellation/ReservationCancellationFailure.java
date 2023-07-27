package com.arsensargsyan.communi.link.service.cancellation;

import com.arsensargsyan.communi.link.common.Failure;
import org.apache.commons.lang3.builder.ToStringBuilder;

public enum ReservationCancellationFailure implements Failure {

    RESERVATION_NOT_FOUND("failure.community.reservation.not.found", "Community reservation not found.");

    private final String code;

    private final String reason;

    ReservationCancellationFailure(final String code, final String reason) {
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