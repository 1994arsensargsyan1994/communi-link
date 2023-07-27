package com.arsensargsyan.communi.link.service.cancellation;

import java.util.Collection;
import java.util.List;

import com.arsensargsyan.communi.link.common.Result;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReservationCancellationResult implements Result<ReservationCancellationFailure> {

    public static final ReservationCancellationResult SUCCESS = new ReservationCancellationResult();

    private final Collection<ReservationCancellationFailure> failures;

    public ReservationCancellationResult() {
        this.failures = null;
    }

    public ReservationCancellationResult(final Collection<ReservationCancellationFailure> failures) {
        this.failures = failures;
    }

    @Override
    public Collection<ReservationCancellationFailure> failures() {
        return failures;
    }

    public static ReservationCancellationResult notFound() {
        return new ReservationCancellationResult(List.of(ReservationCancellationFailure.RESERVATION_NOT_FOUND));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final ReservationCancellationResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("failures", failures())
                .toString();
    }
}