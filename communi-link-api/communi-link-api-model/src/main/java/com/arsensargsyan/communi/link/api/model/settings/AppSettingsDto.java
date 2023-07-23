package com.arsensargsyan.communi.link.api.model.settings;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record AppSettingsDto(String communityName, Set<AppSettingsCommunityType> types) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final AppSettings that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(communityName(), that.communityName())
                .append(types(), that.types())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(communityName())
                .append(types())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("communityName", communityName())
                .append("types", types())
                .toString();
    }
}