package com.arsensargsyan.communi.link.api.facade.creation;

import com.arsensargsyan.communi.link.api.facade.validation.RequestValidator;
import com.arsensargsyan.communi.link.api.facade.validation.ValidationResult;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.response.CommunityCreationResponse;
import com.arsensargsyan.communi.link.service.CommunityService;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationParameter;
import com.arsensargsyan.communi.link.service.creation.CommunityCreationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class DefaultCommunityCreationHandler implements CommunityCreationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultCommunityCreationHandler.class);

    private final RequestValidator<CommunityCreationRequest> requestValidator;

    private final CommunityService communityService;

    DefaultCommunityCreationHandler(
            final RequestValidator<CommunityCreationRequest> requestValidator,
            final CommunityService communityService
    ) {
        this.requestValidator = requestValidator;
        this.communityService = communityService;
    }

    @Override
    public CommunityCreationResponse create(final CommunityCreationRequest request) {
        final ValidationResult validationResult = requestValidator.validate(request);
        final CommunityCreationResponse response = new CommunityCreationResponse();

        if (validationResult.hasFailures()) {
            logger.warn("Create Community validation failed with: {}.", validationResult.failures());
            response.setFailures(validationResult.failures());
            return response;
        }

        final CommunityCreationResult result = communityService.create(
                new CommunityCreationParameter(
                        request.name(), request.type(), request.maxCount()
                )
        );

        if (result.hasFailures()) {
            logger.warn("Create Community failed with: {}.", result.failures());
            response.setResultFailures(result.failures());
            return response;
        }
        response.setCommunityId(result.communityId());
        return response;
    }
}