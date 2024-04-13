package com.saharaj.moneytracker.application.authentication;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.saharaj.moneytracker.application.authentication.service.AppUserDetailService;
import com.saharaj.moneytracker.application.authentication.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticaionFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private AppUserDetailService appUserDetailsService;

    JwtAuthenticaionFilter(JwtService jwtService, AppUserDetailService appUserDetailService) {
        this.jwtService = jwtService;
        this.appUserDetailsService = appUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional<String> jwtToken = jwtService.resolveToken(request);
        if (jwtToken.isPresent()) {
            String subject = jwtService.getTokenSubject(jwtToken.get());

            if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails appUserDetails = appUserDetailsService.loadUserByEmail(subject);

                if (appUserDetails != null && !jwtService.isTokenExpired(jwtToken.get())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            appUserDetails, null);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
