package com.arsensargsyan.communi.link.service;

import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import com.arsensargsyan.communi.link.persistence.community.repository.ReservationRepository;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationFailure;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationParameter;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationResult;
import com.arsensargsyan.communi.link.service.lookup.CommunityLookupService;
import com.arsensargsyan.communi.link.service.lookup.ReservationLookupService;
import com.arsensargsyan.communi.link.service.lookup.ResidentLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultReservationService implements ReservationService {

    private final ReservationLookupService reservationLookupService;

    private final CommunityLookupService communityLookupService;

    private final ResidentLookupService residentLookupService;

    private final ReservationRepository reservationRepository;

    public DefaultReservationService(
            final CommunityLookupService communityLookupService,
            final ResidentLookupService residentLookupService,
            final ReservationLookupService reservationLookupService,
            final ReservationRepository reservationRepository
    ) {
        this.reservationLookupService = reservationLookupService;
        this.communityLookupService = communityLookupService;
        this.residentLookupService = residentLookupService;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public ReservationCreationResult create(final ReservationCreationParameter parameter) {
        Assert.notNull(parameter, "Null parameter was passed.");

        final PersistentCommunity community = communityLookupService.get(parameter.communityId());

        if (community.busy()) {
            return new ReservationCreationResult(ReservationCreationFailure.notAvailablePlace());
        }
        return residentLookupService.lookup(community.id(), parameter.username())
                .flatMap(reservationLookupService::lookup)
                .map(reservation -> new ReservationCreationResult(ReservationCreationFailure.alreadyReserved()))
                .orElseGet(() -> new ReservationCreationResult(save(parameter, community).id()));
    }

    private PersistentReservation save(final ReservationCreationParameter parameter, final PersistentCommunity community) {
        return reservationRepository.save(
                new PersistentReservation(
                        parameter.range().start(),
                        parameter.range().end(),
                        community.currentCount(community.currentCount() + 1),
                        new PersistentResident(
                                parameter.communityId(),
                                parameter.username(),
                                parameter.identifier(),
                                parameter.mail()
                        )
                )
        );
    }
}