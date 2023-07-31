package com.arsensargsyan.communi.link.api.facade.utils.converter;

import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import com.arsensargsyan.communi.link.service.lookup.CommunityDetails;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCommunityDetailsConverter implements CommunityDetailsConverter {

    @Override
    public CommunityDetailsDto convert(@NonNull final CommunityDetails source) {
        Assert.notNull(source, "Null was passed as an argument for parameter 'source'.");
        CommunityDetailsDto detailsDto = new CommunityDetailsDto();
        detailsDto.setId(source.id());
        detailsDto.setName(source.name());
        detailsDto.setType(source.type());
        detailsDto.setMaxCount(source.maxCount());
        detailsDto.setCurrentCount(source.currentCount());
        return detailsDto;
    }
}