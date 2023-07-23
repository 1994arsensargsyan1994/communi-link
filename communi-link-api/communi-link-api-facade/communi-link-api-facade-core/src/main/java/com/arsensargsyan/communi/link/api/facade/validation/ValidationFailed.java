package com.arsensargsyan.communi.link.api.facade.validation;

import java.util.List;

import com.arsensargsyan.communi.link.common.FailureDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

record ValidationFailed(List<FailureDto> failures) implements ValidationResult {

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final ValidationResult that)) {
            return false;
        }
        return new EqualsBuilder()
                .append(failures(), that.failures())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(failures())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("failures", failures())
                .toString();
    }
}