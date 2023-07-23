package com.arsensargsyan.communi.link.api.gateway.errorhandler;

import com.arsensargsyan.communi.link.common.api.model.FailureAwareResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<FailureAwareResponse> onException(Exception ex);

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<FailureAwareResponse> constraintViolationFailed(ConstraintViolationException ex);
}
