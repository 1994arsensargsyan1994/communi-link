package com.arsensargsyan.communi.link.service.lookup.details;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.service.lookup.CommunityDetails;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class ImmutableCommunityDetailsAdapter implements CommunityDetails {

    private final Long id;

    private final String name;

    private final CommunityType type;

    private final Integer maxCount;

    private final Integer currentCount;

    public ImmutableCommunityDetailsAdapter(final PersistentCommunity community) {
        this.id = community.id();
        this.name = community.name();
        this.type = community.type();
        this.maxCount = community.maxCount();
        this.currentCount = community.currentCount();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public CommunityType type() {
        return type;
    }

    @Override
    public Integer maxCount() {
        return maxCount;
    }

    @Override
    public Integer currentCount() {
        return currentCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final CommunityDetails that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(id(), that.id())
                .append(name(), that.name())
                .append(type(), that.type())
                .append(maxCount(), that.maxCount())
                .append(currentCount(), that.currentCount())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id())
                .append(name())
                .append(type())
                .append(maxCount())
                .append(currentCount())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id())
                .append("name", name())
                .append("type", type())
                .append("maxCount", maxCount())
                .append("currentCount", currentCount())
                .toString();
    }
}