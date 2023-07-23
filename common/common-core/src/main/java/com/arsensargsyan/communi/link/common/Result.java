package com.arsensargsyan.communi.link.common;

import java.util.Collection;

public interface Result<F extends Failure> {

    Collection<F> failures();

    default boolean hasFailures() {
        final Collection<? extends Failure> thisFailures = failures();
        return thisFailures != null && !thisFailures.isEmpty();
    }
}