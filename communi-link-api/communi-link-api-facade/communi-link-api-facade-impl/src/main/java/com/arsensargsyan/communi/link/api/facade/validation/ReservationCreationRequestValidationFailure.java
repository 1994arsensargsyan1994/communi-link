package com.arsensargsyan.communi.link.api.facade.validation;

import com.arsensargsyan.communi.link.common.Failure;
import org.apache.commons.lang3.builder.ToStringBuilder;

public enum ReservationCreationRequestValidationFailure implements Failure {

    RESERVATION_START_INVALID_VALUE("failure.reservation.start.invalid.value", "Valid from value is invalid."),
    RESERVATION_END_INVALID_VALUE("failure.reservation.end.invalid.value", "Valid till value is invalid."),
    RESERVATION_PERIOD_INVALID_VALUE("failure.reservation.period.invalid.value", "Invalid reservation period."),
    RESERVATION_PERIOD_INVALID_REQUEST("failure.reservation.period.invalid.request", "Invalid reservation period request.");

    private final String code;

    private final String reason;

    ReservationCreationRequestValidationFailure(final String code, final String reason) {
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