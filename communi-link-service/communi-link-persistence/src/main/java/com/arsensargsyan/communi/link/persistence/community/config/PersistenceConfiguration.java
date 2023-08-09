package com.arsensargsyan.communi.link.persistence.community.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.arsensargsyan.communi.link.persistence")
@EnableJpaRepositories(basePackages = "com.arsensargsyan.communi.link.persistence")
class PersistenceConfiguration {
}