package com.example.paoche.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class TokenSettings {
    private String secretKey;

    private Duration accessTokenExpireTime;

    private Duration refreshTokenExpireTime;

    private Duration refreshTokenExpireAppTime;

    private String issuer;
}
