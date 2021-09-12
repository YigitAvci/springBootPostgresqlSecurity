package com.hunter.springbootpostgresql.core.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@AllArgsConstructor
@Configuration
public class JwtSecretKey {

    @Autowired
    private final JwtConfig jwtConfig;


    @Bean
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
