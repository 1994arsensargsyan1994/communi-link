package com.arsensargsyan.communi.link.api.facade;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationRequest;
import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityCreationResponse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.creation.CommunityCreationHandler;
import com.arsensargsyan.communi.link.api.facade.lookup.CommunityLookupHandler;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityServiceFacadeTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private CommunityCreationHandler communityCreationHandler;

    @Mock
    private CommunityLookupHandler communityLookupHandler;

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

    @Test
    public void testLookupDetails() {
        final Long id = randomId();
        final var response = new LookupCommunityDetailsResponse();
        response.setDetails(new CommunityDetailsDto());

        when(communityLookupHandler.lookupDetails(id)).thenReturn(response);

        Assertions.assertThat(communityServiceFacade.lookupDetails(id)).isEqualTo(response);

        verify(communityLookupHandler).lookupDetails(id);
    }
}