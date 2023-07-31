package com.arsensargsyan.communi.link.api.model.response.details;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommunityDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private CommunityType type;

    @JsonProperty("maxCount")
    private Integer maxCount;

    @JsonProperty("currentCount")
    private Integer currentCount;

    public CommunityDetailsDto() {
        super();
    }

    public Long id() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public CommunityType type() {
        return type;
    }

    public void setType(final CommunityType type) {
        this.type = type;
    }

    public Integer maxCount() {
        return maxCount;
    }

    public void setMaxCount(final Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer currentCount() {
        return currentCount;
    }

    public void setCurrentCount(final Integer currentCount) {
        this.currentCount = currentCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final CommunityDetailsDto that)) {
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