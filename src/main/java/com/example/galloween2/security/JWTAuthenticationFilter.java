package com.example.galloween2.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    public Authentication attempAuthentication (HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{

        AuthenCredentials authCredencials = new AuthenCredentials();

        try {
            authCredencials = new ObjectMapper().readValue(request. getReader(), AuthenCredentials.class);
        } catch (IOException e){

        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
          AuthenCredentials.getEmail(),
          AuthenCredentials.getPassword(),
          Collections.emptyList()

        );
        return AuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void succesfullAthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           FilterChain chain,
                                           Authentication authResult) throws IOException, ServletException{

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
