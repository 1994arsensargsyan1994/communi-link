package com.arsensargsyan.communi.link.persistence.community.repository;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<PersistentReservation, Long> {

    Optional<PersistentReservation> findByResident(PersistentResident resident);
}