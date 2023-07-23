package com.arsensargsyan.communi.link.service;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractCommunityServiceTest {

    @SuppressWarnings("unused")
    protected abstract void verifyNoMoreMockInteractions();

    protected static Long randomId() {
        return RandomUtils.nextLong();
    }

    protected static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}