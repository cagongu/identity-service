package com.spring6framework.identityservice.service;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.spring6framework.identityservice.dto.request.AuthenticationRequest;
import com.spring6framework.identityservice.dto.request.IntrospectRequest;
import com.spring6framework.identityservice.dto.request.LogoutRequest;
import com.spring6framework.identityservice.dto.request.RefreshRequest;
import com.spring6framework.identityservice.dto.response.AuthenticationResponse;
import com.spring6framework.identityservice.dto.response.IntrospectResponse;
import com.spring6framework.identityservice.entities.User;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    String generateToken(User user);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    SignedJWT verifyToken(String token, Boolean isRefresh) throws JOSEException, ParseException;
}
