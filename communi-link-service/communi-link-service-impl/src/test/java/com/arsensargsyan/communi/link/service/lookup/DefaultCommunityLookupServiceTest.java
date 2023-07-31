package com.arsensargsyan.communi.link.service.lookup;

import static com.arsensargsyan.communi.link.service.CommunityServiceTestHelper.persistentCommunity;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.repository.CommunityRepository;
import com.arsensargsyan.communi.link.service.AbstractCommunityServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class DefaultCommunityLookupServiceTest extends AbstractCommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private DefaultCommunityLookupService communityLookupService;

    @Override
    @AfterEach
    protected void verifyNoMoreMockInteractions() {
        verifyNoMoreInteractions(communityRepository);
    }

    @Test
    public void testLookupWithInvalidParameter() {
        Assertions.assertThatThrownBy(() -> communityLookupService.lookup(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testLookupNotExisting() {
        final Long id = randomId();

        when(communityRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThat(communityLookupService.lookup(id)).isEmpty();

        verify(communityRepository).findById(id);
    }

    @Test
    public void testLookupWithId() {
        final Long id = randomId();

        final var community = persistentCommunity(CommunityType.PARKING);

        when(communityRepository.findById(id)).thenReturn(Optional.of(community));

        final Optional<PersistentCommunity> listHolder = communityLookupService.lookup(id);

        Assertions.assertThat(listHolder).isNotEmpty().contains(community);

        verify(communityRepository).findById(id);
    }
}