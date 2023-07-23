package com.arsensargsyan.communi.link.api.model.request;

import com.arsensargsyan.communi.link.api.model.common.ValidatableRequest;
import com.arsensargsyan.communi.link.common.CommunityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommunityCreationRequest extends ValidatableRequest {

    @NotBlank
    @JsonProperty("name")
    @Size(min = 1, max = 32)
    private String name;

    @NotNull
    @JsonProperty("type")
    private CommunityType type;

    @Positive
    @JsonProperty("maxCount")
    private Integer maxCount;

    public CommunityCreationRequest() {
        super();
    }

    public String name() {
        return name;
    }

    public void name(final String name) {
        this.name = name;
    }

    public CommunityType type() {
        return type;
    }

    public void type(final CommunityType type) {
        this.type = type;
    }

    public Integer maxCount() {
        return maxCount;
    }

    public void maxCount(final Integer maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name())
                .append("type", type())
                .append("slotCount", maxCount())
                .toString();
    }
}