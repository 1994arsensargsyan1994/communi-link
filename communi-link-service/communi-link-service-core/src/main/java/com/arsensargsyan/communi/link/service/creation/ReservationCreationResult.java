package com.arsensargsyan.communi.link.service.creation;

import java.util.Collection;

import com.arsensargsyan.communi.link.common.Result;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReservationCreationResult implements Result<ReservationCreationFailure> {

    private final Long reservationId;

    private final Collection<ReservationCreationFailure> failures;

    public ReservationCreationResult(final Long reservationId) {
        this.reservationId = reservationId;
        this.failures = null;
    }

    public ReservationCreationResult(final Collection<ReservationCreationFailure> failures) {
        this.reservationId = null;
        this.failures = failures;
    }

    public Long reservationId() {
        return reservationId;
    }

    @Override
    public Collection<ReservationCreationFailure> failures() {
        return failures;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final ReservationCreationResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(reservationId(), that.reservationId())
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reservationId())
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("reservationId", reservationId())
                .append("failures", failures())
                .toString();
    }
}