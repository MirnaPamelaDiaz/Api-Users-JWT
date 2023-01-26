package com.op.userjwt.security;

import com.op.userjwt.security.JwtService.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService JWT_SERVICE;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        final String AUTHEADER = request.getHeader("Autorization");
        final String JWT;
        final String USER_EMAIL;

        if (AUTHEADER == null || AUTHEADER.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        JWT = AUTHEADER.substring(7);
        USER_EMAIL = JWT_SERVICE.extractUsername(JWT);

    }
}
