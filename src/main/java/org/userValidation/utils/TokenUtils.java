package org.userValidation.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.security.Key;
public class TokenUtils {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username, String email) {
        return Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .signWith(key)
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
