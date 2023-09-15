package com.example.emailService.AppConfig.SecurityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor

public class JtwAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailService;
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
    userEmail = jwtService.extractUserName(jwt);

// todo checking if the user Email has been extracted from the
//  token bt if the context holder is still not
// todo if the context holder is null it means that the user has not ben authenticated
if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
// todo tyring to fetch the user detail from the token
    UserDetails userDetails = this.userDetailService.loadUserByUsername(userEmail);
   //todo the if statement is checking if the token is
    // todo still valid and if the the username
    // todo from token generated is equals to the username in the security userDetails
    if (jwtService.isTokenValid(jwt, userDetails)){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
             userDetails, null , userDetails.getAuthorities()
        );
    }
}

        }
}
