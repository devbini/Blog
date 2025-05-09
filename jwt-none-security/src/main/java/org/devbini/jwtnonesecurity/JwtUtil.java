package org.devbini.jwtnonesecurity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS384);
    private static final long EXPIRATION_TIME = 1000 * 60;

    // 토큰 생성을 위한 메서드
    public static String generateToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 토큰이 정상인지 검증하는 메서드
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 토큰에서 값을 읽는 메서드
    public static String getId(String token) {
        if (validateToken(token)) {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } else {
            return "Invalided!";
        }
    }
}
