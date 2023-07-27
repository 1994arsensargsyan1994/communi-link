package com.arsensargsyan.communi.link.api.facade.cancellation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.service.ReservationService;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationFailure;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationParameter;
import com.arsensargsyan.communi.link.service.cancellation.ReservationCancellationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultReservationCancellationHandlerTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private DefaultReservationCancellationHandler reservationCancellationHandler;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(reservationService);
    }

    @Test
    public void testCancelFailed() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        when(reservationService.cancel(any(ReservationCancellationParameter.class)))
                .thenReturn(new ReservationCancellationResult(List.of(ReservationCancellationFailure.RESERVATION_NOT_FOUND)));

        final var response = reservationCancellationHandler.cancel(communityId, reservationId);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isFailed()).isTrue();

        verify(reservationService).cancel(any(ReservationCancellationParameter.class));
    }

    @Test
    public void testCancel() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        when(reservationService.cancel(any(ReservationCancellationParameter.class))).thenReturn(new ReservationCancellationResult());

        final var response = reservationCancellationHandler.cancel(communityId, reservationId);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isSuccessful()).isTrue();

        verify(reservationService).cancel(any(ReservationCancellationParameter.class));
    }
}