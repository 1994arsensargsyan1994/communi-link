package com.arsensargsyan.communi.link.api.gateway.auth;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
class GatewaySecurityConfiguration {

    public static final List<String> PUBLIC_APIS = List.of(
            "/swagger-ui/**", "/swagger-ui.html",
            "/swagger-resources/**",
            "/swagger-resources",
            "/v3/api-docs/**",
            "/actuator/**", "/monitoring/**"
    );
    private final JwtAuthConverter jwtAuthConverter;

    GatewaySecurityConfiguration(final JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(publicApi()).permitAll()
                        .requestMatchers("/**").hasRole("COMMUNITY_PORTAL")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();

        configuration.setAllowedMethods(List.of(GET.name(), POST.name(), PUT.name(), DELETE.name()));
        configuration.applyPermitDefaultValues();

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }

    public String[] publicApi() {
        return PUBLIC_APIS.toArray(String[]::new);
    }
}