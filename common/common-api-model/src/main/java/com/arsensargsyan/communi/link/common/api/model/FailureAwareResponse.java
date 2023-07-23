package com.arsensargsyan.communi.link.common.api.model;

import java.util.Collection;

import com.arsensargsyan.communi.link.common.Failure;
import com.arsensargsyan.communi.link.common.FailureDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FailureAwareResponse implements Response {

    @JsonProperty("failures")
    private Collection<FailureDto> failures;

    public FailureAwareResponse() {
        super();
    }

    public Collection<FailureDto> getFailures() {
        return failures;
    }

    public void setFailures(final Collection<FailureDto> failures) {
        this.failures = failures;
    }

    public void setResultFailures(final Collection<? extends Failure> failures) {
        this.failures = failures.stream()
                .map(failure -> new FailureDto(failure.code(), failure.reason())).toList();
    }

    @Override
    @JsonProperty
    public boolean isSuccessful() {
        return !isFailed();
    }

    @JsonIgnore
    public boolean isFailed() {
        return failures != null && !failures.isEmpty();
    }

    @Override
    public Collection<FailureDto> failures() {
        return failures;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("failures", failures())
                .toString();
    }
}
