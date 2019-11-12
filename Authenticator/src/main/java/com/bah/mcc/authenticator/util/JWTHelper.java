package com.bah.mcc.authenticator.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bah.mcc.authenticator.jwt.Token;

import java.util.Date;

public class JWTHelper implements JWTUtil {
    @Override
    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("me@me.com")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    @Override
    public Token createToken() {

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            long fiveHoursInMillis = 1000 * 60 * 60 * 5;
            Date expireDate = new Date(System.currentTimeMillis() + fiveHoursInMillis);
            String token = JWT.create()
                    .withSubject("apiuser")
                    .withIssuer("me@me.com")
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
            return new Token(token);
        } catch (JWTCreationException exception){
            return null;
        }
    }
}
