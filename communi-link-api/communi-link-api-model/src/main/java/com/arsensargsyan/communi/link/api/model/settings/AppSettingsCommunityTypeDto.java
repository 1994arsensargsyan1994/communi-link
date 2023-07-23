package com.arsensargsyan.communi.link.api.model.settings;

import com.arsensargsyan.communi.link.common.CommunityType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record AppSettingsCommunityTypeDto(CommunityType type, Boolean isPayable) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final AppSettingsCommunityType that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(type(), that.type())
                .append(isPayable(), that.isPayable())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type())
                .append(isPayable())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("communityType", type())
                .append("isPayable", isPayable())
                .toString();
    }
}