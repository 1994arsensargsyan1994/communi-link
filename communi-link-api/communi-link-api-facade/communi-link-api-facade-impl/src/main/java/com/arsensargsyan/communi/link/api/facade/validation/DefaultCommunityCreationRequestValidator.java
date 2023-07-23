package com.arsensargsyan.communi.link.api.facade.validation;

import java.util.List;

import com.arsensargsyan.communi.link.api.facade.settings.AppSettingsProvider;
import com.arsensargsyan.communi.link.api.model.request.CommunityCreationRequest;
import com.arsensargsyan.communi.link.api.model.settings.AppSettingsCommunityType;
import com.arsensargsyan.communi.link.common.CommunityType;
import org.springframework.stereotype.Component;

@Component
class DefaultCommunityCreationRequestValidator implements RequestValidator<CommunityCreationRequest> {

    private final AppSettingsProvider settingsProvider;

    DefaultCommunityCreationRequestValidator(final AppSettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    @Override
    public ValidationResult validate(final CommunityCreationRequest request) {
        final List<CommunityType> types = settingsProvider.provide().types().stream().map(AppSettingsCommunityType::type).toList();
        if (!types.contains(request.type())) {
            return ValidationResult.failedWith(CommunityCreationRequestValidationFailure.TYPE_INVALID_VALUE);
        }
        return ValidationResult.success();
    }
}