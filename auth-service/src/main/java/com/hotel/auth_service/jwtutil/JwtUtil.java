package com.hotel.auth_service.jwtutil;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

@Component
public class JwtUtil {

    private static final String SECRET = "E++l9xBu2C3GEpMjgk5/SRhCMluSwGrWw3iThO/3K5s=";

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET); // ✅ decode Base64
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // use decoded key
                .compact();
    }
}
