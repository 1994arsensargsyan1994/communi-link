package com.arsensargsyan.communi.link.api.facade;

import com.arsensargsyan.communi.link.api.facade.creation.CommunityCreationHandler;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class DefaultCommunityServiceFacade implements CommunityServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCommunityServiceFacade.class);

    private final CommunityCreationHandler communityCreationHandler;

    DefaultCommunityServiceFacade(final CommunityCreationHandler communityCreationHandler) {
        this.communityCreationHandler = communityCreationHandler;
    }

    @Override
    public CommunityCreationResponse create(final CommunityCreationRequest request) {
        Assert.notNull(request, "Null was passed as an argument for parameter 'request'.");
        logger.debug("Create Community. Request: {}.", request);
        return communityCreationHandler.create(request);
    }
}
