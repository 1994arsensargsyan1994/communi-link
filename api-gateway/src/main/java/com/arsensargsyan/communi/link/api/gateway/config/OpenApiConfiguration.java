package com.arsensargsyan.communi.link.api.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource(value = "classpath:api-documentation.properties")
@OpenAPIDefinition
class OpenApiConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public OpenAPI openAPI(
            @Value("${urm.openapi.info.title}") final String title,
            @Value("${urm.openapi.info.version}") final String version,
            @Value("${urm.openapi.info.description}") final String description
    ) {
        Info info = new Info()
                .title(title)
                .version(version)
                .description(description);

        Components components = new Components().addSecuritySchemes(applicationName, new SecurityScheme()
                .type(SecurityScheme.Type.OPENIDCONNECT)
                .openIdConnectUrl(String.format("%s/.well-known/openid-configuration", issuerUri))
        );

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(applicationName);

        OpenAPI openAPI = new OpenAPI().info(info);

        if (StringUtils.hasLength(issuerUri)) {
            openAPI.components(components)
                    .addSecurityItem(securityRequirement);
        }

        return openAPI;
    }
}