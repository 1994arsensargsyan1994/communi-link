package com.arsensargsyan.communi.link.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.common.DataRange;
import com.arsensargsyan.communi.link.persistence.community.PersistentCommunity;
import com.arsensargsyan.communi.link.persistence.community.PersistentReservation;
import com.arsensargsyan.communi.link.persistence.community.PersistentResident;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.ReservationCreationParameter;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.test.util.ReflectionTestUtils;

public final class CommunityServiceTestHelper {

    private static final String ID = "id";

    private CommunityServiceTestHelper() {
        throw new UnsupportedOperationException();
    }

    public static CommunityCreationParameter communityCreationParameter(final CommunityType type) {
        return new CommunityCreationParameter(
                randomUUID(),
                CommunityType.GYM,
                RandomUtils.nextInt()
        );
    }

    public static ReservationCreationParameter reservationCreationParameter(final Long communityId) {
        return new ReservationCreationParameter(
                communityId,
                randomUUID(),
                randomUUID(),
                randomUUID(),
                new DataRange(LocalDateTime.now(), LocalDateTime.now().plusDays(1))
        );
    }

    public static PersistentCommunity persistentCommunity(final CommunityType type) {
        return new PersistentCommunity(randomUUID(), type, RandomUtils.nextInt());
    }

    public static PersistentResident persistentResident(Long communityId) {
        return new PersistentResident(
                communityId,
                randomUUID(),
                randomUUID(),
                randomUUID()
        );
    }

    public static PersistentReservation persistentReservation(
            final PersistentCommunity community,
            final PersistentResident resident
    ) {
        return new PersistentReservation(LocalDateTime.now(), LocalDateTime.now().plusDays(1), community, resident);
    }

    public static void setId(final Object targetObject, final Long value) {
        ReflectionTestUtils.setField(targetObject, ID, value);
    }

    private static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}