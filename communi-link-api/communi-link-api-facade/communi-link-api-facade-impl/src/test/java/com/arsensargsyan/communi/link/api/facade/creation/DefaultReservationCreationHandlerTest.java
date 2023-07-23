package com.arsensargsyan.communi.link.api.facade.creation;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.reservationCreationRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.api.facade.validation.RequestValidator;
import com.arsensargsyan.communi.link.api.facade.validation.ReservationCreationRequestValidationFailure;
import com.arsensargsyan.communi.link.api.facade.validation.ValidationResult;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.service.ReservationService;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationFailure;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationParameter;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultReservationCreationHandlerTest extends AbstractCommunityServiceFacadeTest {


    @Mock
    private ReservationService reservationService;

    @Mock
    private RequestValidator<ReservationCreationRequest> requestValidator;


    @InjectMocks
    private DefaultReservationCreationHandler reservationCreationHandler;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(reservationService, requestValidator);
    }

    @Test
    public void testReservationWhenValidationFailed() {
        final Long communityId = randomId();
        final var request = reservationCreationRequest();

        when(requestValidator.validate(request)).thenReturn(
                ValidationResult.failedWith(ReservationCreationRequestValidationFailure.RESERVATION_PERIOD_INVALID_VALUE)
        );

        final var response = reservationCreationHandler.reserve(communityId, request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isFailed()).isTrue();

        verify(requestValidator).validate(request);
    }

    @Test
    public void testReservationFailed() {
        final Long communityId = randomId();
        final var request = reservationCreationRequest();

        when(requestValidator.validate(request)).thenReturn(ValidationResult.success());
        when(reservationService.create(any(ReservationCreationParameter.class)))
                .thenReturn(new ReservationCreationResult(List.of(ReservationCreationFailure.COMMUNITY_RESERVATION_NOT_AVAILABLE)));

        final var response = reservationCreationHandler.reserve(communityId, request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isFailed()).isTrue();

        verify(requestValidator).validate(request);
        verify(reservationService).create(any(ReservationCreationParameter.class));
    }

    @Test
    public void testReservation() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        final var request = reservationCreationRequest();

        when(requestValidator.validate(request)).thenReturn(ValidationResult.success());
        when(reservationService.create(any(ReservationCreationParameter.class)))
                .thenReturn(new ReservationCreationResult(reservationId));

        final var response = reservationCreationHandler.reserve(communityId, request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isSuccessful()).isTrue();
        Assertions.assertThat(response.getReservationId()).isEqualTo(reservationId);

        verify(requestValidator).validate(request);
        verify(reservationService).create(any(ReservationCreationParameter.class));
    }
}