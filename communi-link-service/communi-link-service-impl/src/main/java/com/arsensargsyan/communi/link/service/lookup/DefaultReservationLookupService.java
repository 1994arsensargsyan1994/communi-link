package com.arsensargsyan.communi.link.service.lookup;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import com.arsensargsyan.communi.link.persistence.community.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultReservationLookupService implements ReservationLookupService {

    private final ReservationRepository reservationRepository;

    DefaultReservationLookupService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentReservation> lookup(final PersistentResident resident) {
        Assert.notNull(resident, "Null was passed as an argument for parameter 'resident'.");
        return reservationRepository.findByResident(resident);
    }
}
