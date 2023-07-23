package com.arsensargsyan.communi.link.api.facade;

import java.time.LocalDateTime;
import java.util.UUID;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import com.arsensargsyan.communi.link.common.CommunityType;
import org.apache.commons.lang3.RandomUtils;

public final class CommunityServiceFacadeTestHelper {

    private CommunityServiceFacadeTestHelper() {
        throw new UnsupportedOperationException();
    }

    public static CommunityCreationRequest communityCreationRequest() {
        var creationRequest = new CommunityCreationRequest();
        creationRequest.name(randomUUID());
        creationRequest.type(CommunityType.GYM);
        creationRequest.maxCount(5);
        return creationRequest;
    }

    public static ReservationCreationRequest reservationCreationRequest() {
        var request = new ReservationCreationRequest();
        request.username(randomUUID());
        request.mail(randomUUID());
        request.start(LocalDateTime.now());
        request.end(LocalDateTime.now().plusDays(1));
        return request;
    }

    public static CommunityCreationResponse communityCreationResponse() {
        final CommunityCreationResponse response = new CommunityCreationResponse();
        response.setCommunityId(RandomUtils.nextLong());
        return response;
    }

    public static ReservationCreationResponse reservationCreationResponse() {
        final var response = new ReservationCreationResponse();
        response.setReservationId(RandomUtils.nextLong());
        return response;
    }

    private static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}