package com.arsensargsyan.communi.link.api.resource.community.reservation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.ReservationServiceFacade;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.ReservationCancelResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import com.arsensargsyan.communi.link.api.resource.community.AbstractCommunityRestTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultReservationControllerTest extends AbstractCommunityRestTest {

    @Mock
    private ReservationServiceFacade reservationServiceFacade;

    @InjectMocks
    private DefaultReservationController reservationController;


    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(reservationServiceFacade);
    }

    @Test
    public void testReserve() {
        final Long communityId = randomId();
        final var request = new ReservationCreationRequest();
        final var response = new ReservationCreationResponse();
        response.setReservationId(randomId());

        when(reservationServiceFacade.reserve(eq(communityId), eq(request))).thenReturn(response);

        final ResponseEntity<ReservationCreationResponse> creationResponse = reservationController.reserve(communityId, request);

        Assertions.assertThat(creationResponse.getBody()).isEqualTo(response);
        Assertions.assertThat(creationResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(reservationServiceFacade).reserve(eq(communityId), eq(request));
    }

    @Test
    public void testCancel() {
        final Long communityId = randomId();
        final Long reservationId = randomId();

        final var response = new ReservationCancelResponse();

        when(reservationServiceFacade.cancel(eq(communityId), eq(reservationId))).thenReturn(response);

        final ResponseEntity<ReservationCancelResponse> creationResponse = reservationController.cancel(communityId, reservationId);

        Assertions.assertThat(creationResponse.getBody()).isEqualTo(response);
        Assertions.assertThat(creationResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(reservationServiceFacade).cancel(eq(communityId), eq(reservationId));
    }
}