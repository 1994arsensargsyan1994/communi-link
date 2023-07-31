package com.arsensargsyan.communi.link.api.facade.lookup;

import com.arsensargsyan.communi.link.api.facade.utils.converter.CommunityDetailsConverter;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import com.arsensargsyan.communi.link.service.CommunityService;
import com.arsensargsyan.communi.link.service.lookup.LookupCommunityDetailsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultCommunityLookupHandler implements CommunityLookupHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCommunityLookupHandler.class);

    private final CommunityService communityService;

    private final CommunityDetailsConverter detailsConverter;

    public DefaultCommunityLookupHandler(
            final CommunityService communityService,
            final CommunityDetailsConverter detailsConverter
    ) {
        this.communityService = communityService;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public LookupCommunityDetailsResponse lookupDetails(final Long id) {
        final LookupCommunityDetailsResult lookupDetailsResult = communityService.lookupDetails(id);
        final LookupCommunityDetailsResponse response = new LookupCommunityDetailsResponse();

        if (lookupDetailsResult.hasFailures()) {
            logger.warn("Lookup URM list details failed with: {}.", lookupDetailsResult.failures());
            response.setResultFailures(lookupDetailsResult.failures());
            return response;
        }
        response.setDetails(detailsConverter.convert(lookupDetailsResult.details()));
        return response;
    }
}