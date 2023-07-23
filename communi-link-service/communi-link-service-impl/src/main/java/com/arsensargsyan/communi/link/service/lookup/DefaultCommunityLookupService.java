package com.arsensargsyan.communi.link.service.lookup;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultCommunityLookupService implements CommunityLookupService {

    private final CommunityRepository communityRepository;

    DefaultCommunityLookupService(final CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentCommunity> lookup(final Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        return communityRepository.findById(id);
    }
}
