package com.arsensargsyan.communi.link.service.creation;

import java.util.Collection;
import java.util.List;

import com.arsensargsyan.communi.link.common.Failure;
import org.apache.commons.lang3.builder.ToStringBuilder;

public enum ReservationCreationFailure implements Failure {

    COMMUNITY_RESERVATION_NOT_AVAILABLE(
            "failure.community.reservation.not.available.place", "Not Available place for community."
    ),

    RESIDENT_ALREADY_RESERVED(
            "failure.community.reservation.resident.already.reserved", "Resident already reserved."
    );

    private final String code;

    private final String reason;

    ReservationCreationFailure(final String code, final String reason) {
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

    public static Collection<ReservationCreationFailure> notAvailablePlace() {
        return List.of(ReservationCreationFailure.COMMUNITY_RESERVATION_NOT_AVAILABLE);
    }

    public static Collection<ReservationCreationFailure> alreadyReserved() {
        return List.of(ReservationCreationFailure.RESIDENT_ALREADY_RESERVED);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code())
                .append("reason", reason())
                .toString();
    }
}
