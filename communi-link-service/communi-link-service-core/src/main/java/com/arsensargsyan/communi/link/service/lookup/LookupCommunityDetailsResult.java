package com.arsensargsyan.communi.link.service.lookup;

import java.util.Collection;
import java.util.List;

import com.arsensargsyan.communi.link.common.Result;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LookupCommunityDetailsResult implements Result<LookupCommunityDetailsFailure> {

    private final CommunityDetails details;

    private final Collection<LookupCommunityDetailsFailure> failures;

    public LookupCommunityDetailsResult(final CommunityDetails details) {
        this.details = details;
        this.failures = null;
    }

    LookupCommunityDetailsResult(final Collection<LookupCommunityDetailsFailure> failures) {
        this.details = null;
        this.failures = failures;
    }

    public CommunityDetails details() {
        return details;
    }

    public static LookupCommunityDetailsResult notFound() {
        return new LookupCommunityDetailsResult(
                List.of(LookupCommunityDetailsFailure.URM_LIST_NOT_FOUND)
        );
    }

    @Override
    public Collection<LookupCommunityDetailsFailure> failures() {
        return failures;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final LookupCommunityDetailsResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(details(), that.details())
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(details())
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("details", details())
                .append("failures", failures())
                .toString();
    }
}
