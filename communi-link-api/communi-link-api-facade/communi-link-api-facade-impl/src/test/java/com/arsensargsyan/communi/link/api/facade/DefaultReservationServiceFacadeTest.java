package com.arsensargsyan.communi.link.api.facade;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationRequest;
import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationResponse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.creation.ReservationCreationHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultReservationServiceFacadeTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private ReservationCreationHandler reservationCreationHandler;

    @InjectMocks
    private DefaultReservationServiceFacade reservationServiceFacade;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(reservationCreationHandler);
    }

    @Test
    public void testReserveWithInvalidParameters() {
        final Long communityId = randomId();

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.reserve(null, null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.reserve(communityId, null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.reserve(null, reservationCreationRequest()))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testReserve() {
        final Long communityId = randomId();
        final var reservationRequest = reservationCreationRequest();
        final var reservationResponse = reservationCreationResponse();

        when(reservationCreationHandler.reserve(communityId, reservationRequest)).thenReturn(reservationResponse);

        Assertions.assertThat(reservationServiceFacade.reserve(communityId, reservationRequest)).isEqualTo(reservationResponse);

        verify(reservationCreationHandler).reserve(communityId, reservationRequest);
    }
}