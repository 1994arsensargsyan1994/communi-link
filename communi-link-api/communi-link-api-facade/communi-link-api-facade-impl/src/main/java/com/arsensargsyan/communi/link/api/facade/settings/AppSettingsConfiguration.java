package com.arsensargsyan.communi.link.api.facade.settings;

import java.io.IOException;
import java.io.InputStream;

import com.arsensargsyan.communi.link.api.model.settings.AppSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class AppSettingsConfiguration {

    @Bean
    AppSettings appSettings(@Value("${app.settings.json.path:classpath:application-settings.json}") final String path) {
        final Resource resource = new DefaultResourceLoader().getResource(path);
        Assert.state(resource.exists(), "Application settings json file is missing.");

        try (final InputStream inputStream = resource.getInputStream()) {
            return new ObjectMapper().readerFor(AppSettings.class).readValue(inputStream);
        } catch (final IOException ex) {
            throw new IllegalStateException(String.format("Unable to read application settings from: %s", path), ex);
        }
    }
}