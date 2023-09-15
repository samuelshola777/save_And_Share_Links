package com.example.emailService.AppConfig.SecurityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration

public class JtwAuthenticationFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(@NonNull HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        @NonNull FilterChain filterChain) throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
      // todo creating variables to the token
       final String jwt;
       final String userEmail;
       // todo if the request header is null or empty or it doesn't start with Bearer
            //  todo   the method should just end at that point'
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }
    // todo so we start extracting the token from the seventh position
    jwt = authHeader.substring(7);
// todo extracting user name from the token --> jwt
userEmail =

        }
}
