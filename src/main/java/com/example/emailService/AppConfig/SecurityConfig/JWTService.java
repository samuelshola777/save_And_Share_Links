package com.example.emailService.AppConfig.SecurityConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JWTService {

    @Value("${SECURITY_SECRET_KEY}")
    private  String SECRET_KEY;

    public String extractUserName(String token){
        return
    }

    public <T> T extractClaim(String token, Function<Cliams, T> claimResolver)

}
