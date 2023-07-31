package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.facade.creation.CommunityCreationHandler;
import com.arsensargsyan.communi.link.api.facade.lookup.CommunityLookupHandler;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.api.model.response.LookupCommunityDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCommunityServiceFacade implements CommunityServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCommunityServiceFacade.class);

    private final CommunityCreationHandler communityCreationHandler;

    private final CommunityLookupHandler communityLookupHandler;

    DefaultCommunityServiceFacade(
            final CommunityCreationHandler communityCreationHandler,
            final CommunityLookupHandler communityLookupHandler
    ) {
        this.communityCreationHandler = communityCreationHandler;
        this.communityLookupHandler = communityLookupHandler;
    }

    @Override
    public CommunityCreationResponse create(final CommunityCreationRequest request) {
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Community. Request: {}.", request);
        return communityCreationHandler.create(request);
    }

    @Override
    public LookupCommunityDetailsResponse lookupDetails(final Long id) {
        Assert.notNull(id, "Null was passed as an argument for parameter 'id'.");
        logger.debug("Lookup Community details. Id: {}.", id);
        return communityLookupHandler.lookupDetails(id);
    }
}
