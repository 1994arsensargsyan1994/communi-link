package com.arsensargsyan.communi.link.api.model.settings;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record AppSettings(@JsonGetter String communityName, @JsonGetter Set<AppSettingsCommunityType> types) {

    @JsonCreator
    public AppSettings(
            @JsonProperty("name") final String communityName,
            @JsonProperty("types") final Set<AppSettingsCommunityType> types
    ) {
        this.communityName = communityName;
        this.types = types;
    }

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