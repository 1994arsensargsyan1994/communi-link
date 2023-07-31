package com.arsensargsyan.communi.link.api.facade.utils.converter;

import com.arsensargsyan.communi.link.api.model.response.details.CommunityDetailsDto;
import com.arsensargsyan.communi.link.service.lookup.CommunityDetails;
import org.springframework.core.convert.converter.Converter;

public interface CommunityDetailsConverter extends Converter<CommunityDetails, CommunityDetailsDto> {
}