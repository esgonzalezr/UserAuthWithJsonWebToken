package com.up.usersauth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String ACCESS_KEY_TOKEN = "3dec281e1638e5148d6d5908354d494e840d5512b1d37e81c174049784949b96"; // Llave generada en KeyGen.io (SHA256)
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1800L; //1800 segundos = 30 minutos

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000)) //Se pasa a milis.
                .signWith(SignatureAlgorithm.HS256, ACCESS_KEY_TOKEN)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getUsernameFromToken(token)) && !isTokenExpired(token);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder() //Se inicia validación del token
                .setSigningKey(ACCESS_KEY_TOKEN)//Se indica cuál fue la llave con la que se generó el token
                .build()
                .parseClaimsJws(token) //Se parsea el token recibido. Si la firma es inválida, se detiene el proceso de autenticación
                .getBody(); //Se obtienen los claims del token
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
