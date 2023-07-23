package com.arsensargsyan.communi.link.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FailureDto(@JsonProperty("code") String code, @JsonProperty("reason") String reason) {

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code()).append("reason", reason()).toString();
    }
}