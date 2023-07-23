package com.arsensargsyan.communi.link.api.facade;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationRequest;
import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationResponse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.creation.CommunityCreationHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityServiceFacadeTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private CommunityCreationHandler communityCreationHandler;

    @InjectMocks
    private DefaultCommunityServiceFacade communityServiceFacade;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(communityCreationHandler);
    }

    @Test
    public void testCreateWithInvalidParameters() {
        Assertions.assertThatThrownBy(() -> communityServiceFacade.create(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreate() {
        final var creationRequest = communityCreationRequest();
        final var creationResponse = communityCreationResponse();

        when(communityCreationHandler.create(creationRequest)).thenReturn(creationResponse);

        Assertions.assertThat(communityServiceFacade.create(creationRequest)).isEqualTo(creationResponse);

        verify(communityCreationHandler).create(creationRequest);
    }
}