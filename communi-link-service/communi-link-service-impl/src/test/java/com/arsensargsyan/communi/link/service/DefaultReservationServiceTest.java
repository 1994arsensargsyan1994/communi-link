package com.arsensargsyan.communi.link.service;

import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.persistentCommunity;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.persistentReservation;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.persistentResident;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.reservationCancellationParameter;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.reservationCreationParameter;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.setId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.repository.CommunityRepository;
import com.arsensargsyan.communi.link.persistence.community.repository.ReservationRepository;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationFailure;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationResult;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationFailure;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationResult;
import com.arsensargsyan.communi.link.service.lookup.CommunityLookupService;
import com.arsensargsyan.communi.link.service.lookup.ReservationLookupService;
import com.arsensargsyan.communi.link.service.lookup.ResidentLookupService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultReservationServiceTest extends AbstractCommunityServiceTest {

    @Mock
    private ReservationLookupService reservationLookupService;

    @Mock
    private CommunityLookupService communityLookupService;

    @Mock
    private ResidentLookupService residentLookupService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private DefaultReservationService reservationService;

    @Override
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(
                reservationLookupService, communityLookupService, residentLookupService, reservationRepository
        );
    }

    @Test
    public void testCreateWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> reservationService.create(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateWithNotAvailablePlace() {
        final Long communityId = randomId();
        final var persistentCommunity = persistentCommunity(CommunityType.PARKING);
        final var creationParameter = reservationCreationParameter(communityId);

        persistentCommunity.maxCount(1);
        persistentCommunity.incrementCurrentCount();

        when(communityLookupService.get(communityId)).thenReturn(persistentCommunity);

        Assertions.assertThat(reservationService.create(creationParameter)).isEqualTo(new ReservationCreationResult(
                List.of(ReservationCreationFailure.COMMUNITY_RESERVATION_NOT_AVAILABLE)
        ));

        verify(communityLookupService).get(communityId);
    }

    @Test
    public void testCreateWithAlreadyReserved() {
        final Long communityId = randomId();
        final var creationParameter = reservationCreationParameter(communityId);

        final var persistentCommunity = persistentCommunity(CommunityType.PARKING);
        final var persistentResident = persistentResident(communityId);
        final var persistentReservation = persistentReservation(persistentCommunity, persistentResident);

        persistentCommunity.maxCount(10);
        persistentCommunity.incrementCurrentCount();

        when(communityLookupService.get(communityId)).thenReturn(persistentCommunity);
        when(residentLookupService.lookup(persistentCommunity.id(), creationParameter.username()))
                .thenReturn(Optional.of(persistentResident));

        when(reservationLookupService.lookup(persistentResident)).thenReturn(Optional.of(persistentReservation));


        Assertions.assertThat(reservationService.create(creationParameter)).isEqualTo(new ReservationCreationResult(
                List.of(ReservationCreationFailure.RESIDENT_ALREADY_RESERVED)
        ));

        verify(communityLookupService).get(communityId);
    }

    @Test
    public void testCreate() {
        final Long communityId = randomId();
        final var creationParameter = reservationCreationParameter(communityId);

        final var persistentCommunity = persistentCommunity(CommunityType.PARKING);
        final var persistentResident = persistentResident(communityId);

        final var persistentReservation = persistentReservation(persistentCommunity, persistentResident);

        persistentCommunity.maxCount(2);
        persistentCommunity.incrementCurrentCount();

        when(communityLookupService.get(communityId)).thenReturn(persistentCommunity);
        when(residentLookupService.lookup(persistentCommunity.id(), creationParameter.username())).thenReturn(Optional.empty());
        when(reservationRepository.save(any(PersistentReservation.class))).thenReturn(persistentReservation);

        final var result = reservationService.create(creationParameter);

        Assertions.assertThat(result.hasFailures()).isFalse();
        Assertions.assertThat(result.reservationId()).isEqualTo(persistentReservation.id());

        verify(communityLookupService).get(communityId);
        verify(residentLookupService).lookup(persistentCommunity.id(), creationParameter.username());
        verify(reservationRepository).save(any(PersistentReservation.class));
    }

    @Test
    public void testCancelReservationNotFound() {
        final var parameter = reservationCancellationParameter();

        when(reservationLookupService.lookup(parameter.reservationId(), parameter.communityId()))
                .thenReturn(Optional.empty());

        Assertions.assertThat(reservationService.cancel(parameter)).isEqualTo(new ReservationCancellationResult(
                List.of(ReservationCancellationFailure.RESERVATION_NOT_FOUND)
        ));

        verify(reservationLookupService).lookup(parameter.reservationId(), parameter.communityId());
    }

    @Test
    public void testCancel() {
        final var community = persistentCommunity();
        setId(community, randomId());

        final var reservation = persistentReservation(community);
        setId(reservation, randomId());

        final var parameter = reservationCancellationParameter(reservation.id(), community.id());

        when(reservationLookupService.lookup(parameter.reservationId(), parameter.communityId()))
                .thenReturn(Optional.of(reservation));

        doNothing().when(reservationRepository).deleteById(parameter.reservationId());
        doNothing().when(communityRepository).cancel(parameter.communityId());

        Assertions.assertThat(reservationService.cancel(parameter)).isEqualTo(ReservationCancellationResult.SUCCESS);

        verify(reservationLookupService).lookup(parameter.reservationId(), parameter.communityId());
        verify(reservationRepository).deleteById(parameter.reservationId());
        verify(communityRepository).cancel(parameter.communityId());
    }
}