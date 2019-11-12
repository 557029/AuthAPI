package com.bah.mcc.authenticator.util;

import com.bah.mcc.authenticator.jwt.Token;

public interface JWTUtil {
    boolean verifyToken(String token);
    Token createToken();
}
