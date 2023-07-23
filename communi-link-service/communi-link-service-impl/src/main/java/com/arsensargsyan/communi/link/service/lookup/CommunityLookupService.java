package com.arsensargsyan.communi.link.service.lookup;

import java.util.Optional;

import com.arsensargsyan.communi.link.common.exception.MissingCommunityException;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;

public interface CommunityLookupService {

    Optional<PersistentCommunity> lookup(Long id);

    default PersistentCommunity get(final Long id) {
        return lookup(id).orElseThrow(() ->
                new MissingCommunityException(
                        String.format("Community not found. Id: %s.", id)
                )
        );
    }
}
