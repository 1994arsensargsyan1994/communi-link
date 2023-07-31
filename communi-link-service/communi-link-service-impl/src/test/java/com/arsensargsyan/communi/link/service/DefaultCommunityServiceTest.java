package com.arsensargsyan.communi.link.service;

import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.communityCreationParameter;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.persistentCommunity;
import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.setId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.repository.CommunityRepository;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationFailure;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;
import com.arsensargsyan.communi.link.service.lookup.CommunityLookupService;
import com.arsensargsyan.communi.link.service.lookup.LookupCommunityDetailsResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityServiceTest extends AbstractCommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @Mock
    private CommunityLookupService communityLookupService;

    @InjectMocks
    private DefaultCommunityService communityService;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(communityRepository, communityLookupService);
    }

    @Test
    public void testCreateWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> communityService.create(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateAlreadyExistingList() {
        final var parameter = communityCreationParameter(CommunityType.GYM);

        when(communityRepository.existsByNameAndType(parameter.name(), parameter.type())).thenReturn(true);

        Assertions.assertThat(communityService.create(parameter)).isEqualTo(new CommunityCreationResult(
                List.of(CommunityCreationFailure.COMMUNITY_ALREADY_EXISTS)
        ));

        verify(communityRepository).existsByNameAndType(parameter.name(), parameter.type());
    }

    @Test
    public void testCreate() {
        final var community = persistentCommunity(CommunityType.GYM);
        setId(community, randomId());

        final var parameter = communityCreationParameter(community.type());

        when(communityRepository.existsByNameAndType(parameter.name(), parameter.type())).thenReturn(false);
        when(communityRepository.save(any(PersistentCommunity.class))).thenReturn(community);

        final var result = communityService.create(parameter);

        Assertions.assertThat(result.hasFailures()).isFalse();
        Assertions.assertThat(result.communityId()).isEqualTo(community.id());

        verify(communityRepository).existsByNameAndType(parameter.name(), parameter.type());
        verify(communityRepository).save(any(PersistentCommunity.class));
    }

    @Test
    public void testLookupNotExistingCommunityDetails() {
        final Long id = randomId();

        when(communityLookupService.lookup(id)).thenReturn(Optional.empty());

        Assertions.assertThat(communityService.lookupDetails(id))
                .isEqualTo(LookupCommunityDetailsResult.notFound());

        verify(communityLookupService).lookup(id);
    }

    @Test
    public void testLookupDetails() {
        final Long id = randomId();
        final var list = persistentCommunity(CommunityType.GYM);

        when(communityLookupService.lookup(id)).thenReturn(Optional.of(list));

        final LookupCommunityDetailsResult result = communityService.lookupDetails(id);

        Assertions.assertThat(result.hasFailures()).isFalse();
        Assertions.assertThat(result.details().id()).isEqualTo(list.id());
        Assertions.assertThat(result.details().name()).isEqualTo(list.name());
        Assertions.assertThat(result.details().type()).isEqualTo(list.type());

        Assertions.assertThat(result.details().maxCount()).isEqualTo(list.maxCount());
        Assertions.assertThat(result.details().currentCount()).isEqualTo(list.currentCount());

        verify(communityLookupService).lookup(id);
    }
}