package com.arsensargsyan.communi.link.api.facade.validation;

import com.arsensargsyan.communi.link.api.model.common.ValidatableRequest;

public interface RequestValidator<V extends ValidatableRequest> {

    default ValidationResult validate(final V request) {
        return ValidationResult.success();
    }
}