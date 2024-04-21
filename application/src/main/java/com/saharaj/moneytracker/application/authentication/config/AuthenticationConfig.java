package com.saharaj.moneytracker.application.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.saharaj.moneytracker.application.authentication.JwtAuthenticaionFilter;


@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

    UserDetailsService userDetailsService;
    JwtAuthenticaionFilter jwtAuthenticaionFilter;

    AuthenticationConfig (UserDetailsService userDetailService, JwtAuthenticaionFilter jwtAuthenticaionFilter) {
        this.userDetailsService = userDetailService;
        this.jwtAuthenticaionFilter = jwtAuthenticaionFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //TODO: implement CSRF and XSS security config
        return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(registry -> {
            registry.requestMatchers("/auth/**").permitAll();
            registry.requestMatchers("/account/**").authenticated();
        })
        .addFilterBefore(jwtAuthenticaionFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin(formLoginConfig -> formLoginConfig.permitAll())
        .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // DaoAuthenticationProvider for DB authentication
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
