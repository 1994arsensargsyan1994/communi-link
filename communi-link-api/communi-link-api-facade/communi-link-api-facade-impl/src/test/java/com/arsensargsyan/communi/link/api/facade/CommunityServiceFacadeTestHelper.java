package com.arsensargsyan.communi.link.api.facade;

import java.time.LocalDateTime;
import java.util.UUID;

import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.request.ReservationCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.ReservationCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.service.lookup.CommunityDetails;
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

    public static CommunityDetails communityDetails() {
        return new DummyCommunityDetails(CommunityType.SPA);
    }

    public static CommunityDetailsDto communityDetailsDto() {
        final var detailsDto = new CommunityDetailsDto();

        detailsDto.setId(RandomUtils.nextLong());
        detailsDto.setName(randomUUID());
        detailsDto.setType(CommunityType.SPA);
        detailsDto.setMaxCount(RandomUtils.nextInt());
        detailsDto.setCurrentCount(RandomUtils.nextInt());
        return detailsDto;
    }

    private static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    private static final class DummyCommunityDetails implements CommunityDetails {

        private final Long id = RandomUtils.nextLong();

        private final String name = randomUUID();

        private final CommunityType type;

        private final Integer maxCount = RandomUtils.nextInt();

        private final Integer currentCount = maxCount - 1;

        DummyCommunityDetails(final CommunityType type) {
            this.type = type;
        }

        @Override
        public Long id() {
            return id;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public CommunityType type() {
            return type;
        }

        @Override
        public Integer maxCount() {
            return maxCount;
        }

        @Override
        public Integer currentCount() {
            return currentCount;
        }
    }
}