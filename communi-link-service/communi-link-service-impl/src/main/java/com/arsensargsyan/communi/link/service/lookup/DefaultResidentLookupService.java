package com.arsensargsyan.communi.link.service.lookup;

import java.util.Optional;

import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import com.arsensargsyan.communi.link.persistence.community.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultResidentLookupService implements ResidentLookupService {

    private final ResidentRepository repository;

    DefaultResidentLookupService(final ResidentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersistentResident> lookup(final Long communityId, final String username) {
        Assert.notNull(communityId, "Null was passed as an argument for parameter 'communityId'.");
        Assert.hasText(username, "Null or empty text was passed as an argument for parameter 'username'.");
        return repository.findByCommunityIdAndUsername(communityId, username);
    }
}
