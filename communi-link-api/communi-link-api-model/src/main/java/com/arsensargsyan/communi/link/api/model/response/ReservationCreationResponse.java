package com.arsensargsyan.communi.link.api.model.response;

import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReservationCreationResponse extends FailureAwareResponse {

    @JsonProperty("reservationId")
    private Long reservationId;

    public ReservationCreationResponse() {
        super();
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(final Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("reservationId", getReservationId())
                .toString();
    }
}