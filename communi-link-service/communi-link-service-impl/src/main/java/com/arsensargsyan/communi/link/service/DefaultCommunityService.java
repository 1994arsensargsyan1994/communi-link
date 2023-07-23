package com.arsensargsyan.communi.link.service;

import java.util.List;

import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.repository.CommunityRepository;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationFailure;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultCommunityService implements CommunityService {

    private final CommunityRepository communityRepository;

    public DefaultCommunityService(final CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    @Transactional
    public CommunityCreationResult create(final CommunityCreationParameter parameter) {
        Assert.notNull(parameter, "Null was passed as an argument for parameter 'parameter'.");
        return isAlreadyExists(parameter)
                ? new CommunityCreationResult(List.of(CommunityCreationFailure.COMMUNITY_ALREADY_EXISTS))
                : new CommunityCreationResult(createCommunity(parameter).id());
    }

    private PersistentCommunity createCommunity(final CommunityCreationParameter param) {
        return communityRepository.save(
                new PersistentCommunity(
                        param.name(),
                        param.type(),
                        param.maxCount()
                )
        );
    }

    private boolean isAlreadyExists(final CommunityCreationParameter parameter) {
        return communityRepository.existsByNameAndType(parameter.name(), parameter.type());
    }
}