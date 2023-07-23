package com.arsensargsyan.communi.link.common.exception;

public class MissingReservationException extends RuntimeException {

    public MissingReservationException(final Long communityId) {
        super(String.format("Reservation for Community with id: %s is missing!", communityId));
    }
}