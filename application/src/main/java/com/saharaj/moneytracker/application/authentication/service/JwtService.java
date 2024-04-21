package com.saharaj.moneytracker.application.authentication.service;

import org.springframework.stereotype.Service;

import com.saharaj.moneytracker.application.authentication.model.AppUser;

import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest; 

@Service
public class JwtService {
    private static final String SECRET = "your-secret-key";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public String generateToken(AppUser user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    public Optional <String> resolveToken (HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return Optional.ofNullable(bearerToken.substring(TOKEN_PREFIX.length()));
        }
        return Optional.empty();
    }

    public String getTokenSubject(String token) {
        // subject is user email
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        Claims jwtClaims = getClaims(token);
        if (new Date(System.currentTimeMillis()).before(jwtClaims.getExpiration())){
            return false;
        }
        return true;        
    }
}
