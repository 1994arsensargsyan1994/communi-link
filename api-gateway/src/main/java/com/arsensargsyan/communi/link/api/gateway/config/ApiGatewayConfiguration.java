package com.arsensargsyan.communi.link.api.gateway.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({"com.arsensargsyan.communi.link.api.client"})
class ApiGatewayConfiguration {
}