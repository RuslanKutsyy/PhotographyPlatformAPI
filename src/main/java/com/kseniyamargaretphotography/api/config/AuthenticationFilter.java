package com.kseniyamargaretphotography.api.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kseniyamargaretphotography.api.DTO.UserLoginDTO;
import com.kseniyamargaretphotography.api.exceptions.ValidationFailedException;
import com.kseniyamargaretphotography.api.helper.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final MessageSource messageSource;

    public AuthenticationFilter(AuthenticationManager authenticationManager, MessageSource messageSource) {
        this.authenticationManager = authenticationManager;
        this.messageSource = messageSource;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginDTO userLoginDTO;

        try {
            userLoginDTO = objectMapper.readValue(request.getReader(), UserLoginDTO.class);
        } catch (Exception ex) {
            log.error("Failed to extract credentials from request");
            throw new UsernameNotFoundException("Invalid authentication data provided");
        }

        log.info("Username is: {}", userLoginDTO.getUserName());
        log.info("Password is: {}", userLoginDTO.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(), userLoginDTO.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(JwtUtil.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        String accessToken = JwtUtil.generateToken(user, request);
        String refreshToken = JwtUtil.generateRefreshToken(user, request);

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
