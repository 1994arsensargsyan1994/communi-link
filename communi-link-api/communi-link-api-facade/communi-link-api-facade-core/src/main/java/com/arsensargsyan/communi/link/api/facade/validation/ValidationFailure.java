package com.arsensargsyan.communi.link.api.facade.validation;

import com.arsensargsyan.communi.link.common.Failure;

public interface ValidationFailure extends Failure {

    default ValidationResult asFailedResult() {
        return ValidationResult.failedWith(this);
    }
}