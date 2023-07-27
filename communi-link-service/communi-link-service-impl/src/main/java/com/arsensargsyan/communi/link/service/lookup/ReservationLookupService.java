package com.arsensargsyan.communi.link.service.lookup;

import java.time.LocalDateTime;
import java.util.Optional;

import com.arsensargsyan.communi.link.common.DataRange;
import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.PersistentResident;

public interface ReservationLookupService {

    Optional<PersistentReservation> lookup(PersistentResident resident);

    Optional<PersistentReservation> lookup(Long id, Long commodityId);

    // A feature should use for resident can reserve with no overlap data ranges
    default Optional<PersistentReservation> withInvalidDuration(
            LocalDateTime start, int days, PersistentResident resident
    ) {
        return lookup(resident).filter(reservation ->
                DataRange.duration(start, reservation.end(), days)
        );
    }
}
