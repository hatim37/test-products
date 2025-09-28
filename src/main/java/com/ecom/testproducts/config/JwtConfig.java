package com.ecom.testproducts.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

    public JwtConfig(RsakeysConfig rsakeysConfig) {
        this.rsakeysConfig = rsakeysConfig;
    }


    private RsakeysConfig rsakeysConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Qualifier("userJwtDecoder")
    public JwtDecoder UserjwtDecoder() {

        return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    }

    @Bean
    @Qualifier("resourceJwtDecoder")
    public JwtDecoder resourceJwtDecoder() {
        return NimbusJwtDecoder
                .withJwkSetUri("http://localhost:8091/api/oauth2/jwks")
                .build();
    }
}
