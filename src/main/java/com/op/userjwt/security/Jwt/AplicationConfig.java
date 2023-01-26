package com.op.userjwt.security.Jwt;

import com.op.userjwt.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AplicationConfig {
    private final UserAppRepository REPOSITORY;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> REPOSITORY.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
