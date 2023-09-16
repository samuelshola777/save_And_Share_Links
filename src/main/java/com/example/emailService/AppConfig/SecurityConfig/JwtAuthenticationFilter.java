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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailService;
        @Override
        protected void doFilterInternal(@NonNull HttpServletRequest request
                ,@NonNull HttpServletResponse response,
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
    // todo so we start extracting the token from the seventh position of the header of the request

            jwt = authHeader.substring(7);
// todo after extracting the token from the header of the request

    // todo extracting user name from the token --> jwt

            userEmail = jwtService.extractUserName(jwt);

// todo checking if the user Email has been extracted from the
//  token and checking if the security context holder is null
//todo which means the user has not been authenticated
// todo if the context holder is null it means that the user has not ben authenticated

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

                // todo tyring to fetch the user detail from the token

    UserDetails userDetails = this.userDetailService.loadUserByUsername(userEmail);

    // todo the UserDetailService userDetailService = this.userDetailService(userEmail);
// todo is fetching the user from the database with the user email

    if (jwtService.isTokenValid(jwt, userDetails)){

    // todo  is checking if the username that was fetch from the database belongs to the
// todo is equals to the username on the token


// todo this is the part i got confused >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
    filterChain.doFilter(request, response);

        }



}
