package com.arsensargsyan.communi.link.api.resource.community;

import java.time.LocalDateTime;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.common.CommunityType;

public final class CommunityControllerIntegrationTestHelper {

    public static final long COMMUNITY_ID = 1L;
    public static final long RESERVATION_ID = 1L;

    private static final CommunityType COMMUNITY_TYPE = CommunityType.GYM;
    private static final String COMMUNITY_NAME = "AMAZON";
    private static final int COMMUNITY_MAX_COUNT = 5;

    private static final String PASSPORT_NUMBER = "PASSPORT_NUMBER";
    private static final String USERNAME = "USERNAME";
    private static final String MAIL = "example@hh.com";

    public static CommunityCreationRequest communityCreationRequest() {
        final var request = new CommunityCreationRequest();
        request.name(COMMUNITY_NAME);
        request.type(COMMUNITY_TYPE);
        request.maxCount(COMMUNITY_MAX_COUNT);
        return request;
    }

    public static ReservationCreationRequest reservationCreationRequest() {
        final ReservationCreationRequest request = new ReservationCreationRequest();
        request.start(LocalDateTime.now().plusHours(1));
        request.end(LocalDateTime.now().plusHours(2));
        request.username(USERNAME);
        request.mail(MAIL);
        request.identifier(PASSPORT_NUMBER);
        return request;
    }
}