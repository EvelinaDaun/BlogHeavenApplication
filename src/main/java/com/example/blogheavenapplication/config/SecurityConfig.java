package com.example.blogheavenapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


// Klass: SecurityConfig - Inställningar för säkerheten i applikationen

@Configuration         // Markerar klassen som en konfigurationsklass
@EnableWebSecurity     // Aktiverar Spring Securitys webbrelaterade säkerhetsfunktioner
@EnableMethodSecurity  // Aktiverar metodnivåssäkerhet
public class SecurityConfig {

    @Autowired
    private JwtAuthConverter jwtAuthConverter; // Konverterar en JWT till en autentiseringstoken


    // Metod: Konfigurerar säkerhetsreglerna för HTTP förfrågningar
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        /* Definierar regler för HTTP förfrågningar */
        http
                .csrf((csrf) -> csrf.disable())  // Inaktivera CSRF
                .authorizeHttpRequests((auth) -> // De URL som är tillgängliga med ocn utan autentisering
                        auth
                                .requestMatchers("/api/posts").permitAll()      // Hämta alla blogginlägg
                                .requestMatchers("/api/posts/**").permitAll()   // Hämta specifikt blogginlägg med id
                                .requestMatchers("/api/newuser").permitAll()    // Lägga till en ny användare
                                .anyRequest().authenticated());                   // Alla andra förfrågningar måste användaren vara autentiserade

        /* OAuth2 konfiguration */
        http
                .oauth2ResourceServer(ors ->
                        ors.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        /* Session konfiguration */
        http
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(STATELESS)); // Servern lagrar inga sessioner

        return http.build();
    }

}
