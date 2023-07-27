package com.arsensargsyan.communi.link.api.facade;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationRequest;
import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationResponse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.cancellation.ReservationCancellationHandler;
import com.arsensargsyan.communi.link.api.facade.creation.ReservationCreationHandler;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultReservationServiceFacadeTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private ReservationCreationHandler reservationCreationHandler;

    @Mock
    private ReservationCancellationHandler reservationCancellationHandler;

    @InjectMocks
    private DefaultReservationServiceFacade reservationServiceFacade;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(reservationCreationHandler, reservationCancellationHandler);
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

    @Test
    public void testCancelWithInvalidParameters() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.cancel(null, null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.cancel(communityId, null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> reservationServiceFacade.cancel(null, reservationId))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void testCancel() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        final var reservationResponse = new ReservationCancelResponse();

        when(reservationCancellationHandler.cancel(communityId, reservationId)).thenReturn(reservationResponse);

        Assertions.assertThat(reservationServiceFacade.cancel(communityId, reservationId)).isEqualTo(reservationResponse);

        verify(reservationCancellationHandler).cancel(communityId, reservationId);
    }
}