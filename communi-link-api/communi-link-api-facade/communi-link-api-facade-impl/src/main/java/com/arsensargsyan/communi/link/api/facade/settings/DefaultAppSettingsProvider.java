package com.arsensargsyan.communi.link.api.facade.settings;

import com.arsensargsyan.communi.link.api.model.settings.AppSettings;
import com.arsensargsyan.communi.link.api.model.settings.AppSettingsDto;
import org.springframework.stereotype.Component;

@Component
class DefaultAppSettingsProvider implements AppSettingsProvider {

    private final AppSettings appSettings;

    DefaultAppSettingsProvider(final AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    @Override
    public AppSettingsDto provide() {
        return new AppSettingsDto(appSettings.communityName(), appSettings.types());
    }
}