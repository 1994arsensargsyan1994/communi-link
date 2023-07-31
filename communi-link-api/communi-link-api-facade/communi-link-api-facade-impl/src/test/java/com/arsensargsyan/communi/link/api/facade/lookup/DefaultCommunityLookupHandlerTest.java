package com.arsensargsyan.communi.link.api.facade.lookup;

import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityDetails;
import static com.arsensargsyan.communi.link.api.facade.CommunityServiceFacadeTestHelper.communityDetailsDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.arsensargsyan.communi.link.api.facade.AbstractCommunityServiceFacadeTest;
import com.arsensargsyan.communi.link.api.facade.utils.converter.CommunityDetailsConverter;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import com.arsensargsyan.communi.link.service.CommunityService;
import com.arsensargsyan.communi.link.service.lookup.LookupCommunityDetailsResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityLookupHandlerTest extends AbstractCommunityServiceFacadeTest {

    @Mock
    private CommunityService communityService;

    @Mock
    private CommunityDetailsConverter detailsConverter;

    @InjectMocks
    private DefaultCommunityLookupHandler communityLookupHandler;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(communityService, detailsConverter);
    }

    @Test
    public void testLookupDetailsFailed() {
        final Long id = randomId();

        when(communityService.lookupDetails(id)).thenReturn(LookupCommunityDetailsResult.notFound());

        final LookupCommunityDetailsResponse lookupDetailsResponse = communityLookupHandler.lookupDetails(id);

        Assertions.assertThat(lookupDetailsResponse).isNotNull();
        Assertions.assertThat(lookupDetailsResponse.isFailed()).isTrue();

        verify(communityService).lookupDetails(id);
        verify(detailsConverter, never()).convert(any());
    }

    @Test
    public void testLookupDetails() {
        final Long id = randomId();

        final var details = communityDetails();
        final var detailsDto = communityDetailsDto();

        when(communityService.lookupDetails(id))
                .thenReturn(new LookupCommunityDetailsResult(details));
        when(detailsConverter.convert(details)).thenReturn(detailsDto);

        final LookupCommunityDetailsResponse lookupDetailsResponse = communityLookupHandler.lookupDetails(id);

        Assertions.assertThat(lookupDetailsResponse).isNotNull();
        Assertions.assertThat(lookupDetailsResponse.isSuccessful()).isTrue();
        Assertions.assertThat(lookupDetailsResponse.getDetails()).isEqualTo(detailsDto);

        verify(communityService).lookupDetails(id);
        verify(detailsConverter).convert(details);
    }
}