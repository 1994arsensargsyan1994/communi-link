package com.arsensargsyan.communi.link.service.creation;

import java.util.Collection;

import com.arsensargsyan.communi.link.common.Result;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class CommunityCreationResult implements Result<CommunityCreationFailure> {

    private final Long communityId;

    private final Collection<CommunityCreationFailure> failures;

    public CommunityCreationResult(final Long communityId) {
        this.communityId = communityId;
        this.failures = null;
    }

    public CommunityCreationResult(final Collection<CommunityCreationFailure> failures) {
        this.communityId = null;
        this.failures = failures;
    }

    public Long communityId() {
        return communityId;
    }

    @Override
    public Collection<CommunityCreationFailure> failures() {
        return failures;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final CommunityCreationResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(communityId(), that.communityId())
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(communityId())
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("communityId", communityId())
                .append("failures", failures())
                .toString();
    }
}