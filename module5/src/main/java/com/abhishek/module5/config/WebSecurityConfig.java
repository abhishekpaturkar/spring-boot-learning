package com.abhishek.module5.config;

import com.abhishek.module5.filters.JwtAuthFilter;
import com.abhishek.module5.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/posts", "/auth/**", "/home.html").permitAll()
                        .requestMatchers("/posts/**").authenticated()
                        .anyRequest().authenticated()
                )
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionManageConfig ->
                    sessionManageConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Putting filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2LoginConfig -> oauth2LoginConfig
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );


        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
         return config.getAuthenticationManager();
    }
}
