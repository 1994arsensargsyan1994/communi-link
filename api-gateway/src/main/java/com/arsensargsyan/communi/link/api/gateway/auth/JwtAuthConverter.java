package com.arsensargsyan.communi.link.api.gateway.auth;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.converter.ClaimConversionService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String CLIENT_ROLES_ELEMENT_IN_JWT = "roles";

    private static final String CLIENT_NAME_ELEMENT_IN_JWT = "resource_access";

    private static final TypeDescriptor SOURCE_TYPE = TypeDescriptor.valueOf(Object.class);

    private static final TypeDescriptor TARGET_TYPE = TypeDescriptor.collection(
            List.class, TypeDescriptor.valueOf(String.class)
    );

    private static final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(final Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                        jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        extractResourceRoles(jwt).stream()
                )
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        return conv(jwt);
    }

    public Collection<GrantedAuthority> conv(final Jwt jwt) {
        return jwt.getClaimAsMap(CLIENT_NAME_ELEMENT_IN_JWT).values().stream()
                .map(JwtAuthConverter::roles)
                .flatMap(Collection::stream)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @SuppressWarnings("unchecked")
    private static Collection<String> roles(final Object object) {
        final Map<String, Object> map = (Map<String, Object>) object;
        return (Collection<String>) ClaimConversionService.getSharedInstance().convert(
                map.get(CLIENT_ROLES_ELEMENT_IN_JWT), SOURCE_TYPE, TARGET_TYPE
        );
    }
}