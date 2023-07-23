package com.arsensargsyan.communi.link.common.api.model;

import java.util.Collection;
import java.util.List;

import com.arsensargsyan.communi.link.common.FailureDto;

public interface Response {

    default Collection<FailureDto> failures() {
        return List.of();
    }

    default boolean isSuccessful() {
        final Collection<FailureDto> failures = failures();
        return failures == null || failures.isEmpty();
    }
}