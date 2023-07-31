package com.arsensargsyan.communi.link.api.model.response;

import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LookupCommunityDetailsResponse extends FailureAwareResponse {

    @JsonProperty("details")
    private CommunityDetailsDto details;

    public CommunityDetailsDto getDetails() {
        return details;
    }

    public void setDetails(final CommunityDetailsDto details) {
        this.details = details;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final LookupCommunityDetailsResponse that)) {
            return false;
        }
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getDetails(), that.getDetails())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getDetails())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("details", getDetails())
                .toString();
    }
}