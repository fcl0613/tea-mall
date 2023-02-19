package com.wwx.teamall.config;

import com.wwx.teamall.utils.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}
