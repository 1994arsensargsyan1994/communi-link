package com.arsensargsyan.communi.link.api.resource.community.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(
        basePackages = "com.arsensargsyan.communi.link.persistence"
)
@EnableJpaRepositories(
        basePackages = "com.arsensargsyan.communi.link.persistence"
)
@ComponentScan(
        basePackages = {
                "com.arsensargsyan.communi.link.service",
                "com.arsensargsyan.communi.link.api.resource.community",
                "com.arsensargsyan.communi.link.api.facade",
        }
)
@Configuration
@EnableAutoConfiguration
public class CommunityControllerIntegrationTestConfig {
}