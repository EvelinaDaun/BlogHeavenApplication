package com.example.blogheavenapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Klass: JwtAuthConverter - Konverterar en JWT till en AbstractAuthenticationToken.

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    // Tar ut roller och auktoriteter från en JWT
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    // Delar av token - Värdet hämtas från konfigurationsfilen
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;
    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;

    // Metod: Från en JWT till AbstractAuthenticationToken
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt){
        Collection<GrantedAuthority> authorities = Stream.concat(
                        jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        extractResourceRoles(jwt).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipleClaimName(jwt));
    }

    // Metod: Hämtar namn från JWT
    private String getPrincipleClaimName(Jwt jwt){
        String claimName = JwtClaimNames.SUB;
        if(principleAttribute != null){
            claimName = principleAttribute;
        }
        return jwt.getClaim(claimName);
    }

    // Metod: Hämtar roller från resource_access i JWT
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt){
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if(jwt.getClaim("resource_access") == null){
            return Set.of();
        }

        resourceAccess = jwt.getClaim("resource_access");

        if(resourceAccess.get(resourceId) == null){
            return Set.of();
        }

        resource = (Map<String, Object>) resourceAccess.get(resourceId);
        resourceRoles = (Collection<String>) resource.get("roles");

        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

}
