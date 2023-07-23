package com.arsensargsyan.communi.link.api.resource.community;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.CommunityServiceFacade;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultCommunityControllerTest extends AbstractCommunityRestTest {

    @Mock
    private CommunityServiceFacade communityServiceFacade;

    @InjectMocks
    private DefaultCommunityController communityController;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(communityServiceFacade);
    }

    @Test
    public void testCreate() {
        final var request = new CommunityCreationRequest();
        final var response = new CommunityCreationResponse();
        response.setCommunityId(randomId());

        when(communityServiceFacade.create(eq(request))).thenReturn(response);

        final ResponseEntity<CommunityCreationResponse> creationResponse = communityController.create(request);

        Assertions.assertThat(creationResponse.getBody()).isEqualTo(response);
        Assertions.assertThat(creationResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(communityServiceFacade).create(eq(request));
    }

}
