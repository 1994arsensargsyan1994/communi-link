package com.arsensargsyan.communi.link.api.resource.community;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractCommunityRestTest {

    @SuppressWarnings("unused")
    protected abstract void verifyNoMoreMockInteractions();

    protected static Long randomId() {
        return RandomUtils.nextLong();
    }
}