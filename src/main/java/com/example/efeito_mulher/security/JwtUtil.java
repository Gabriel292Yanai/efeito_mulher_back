package com.example.efeito_mulher.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiracao}")
    private long expiracao;

    public String gerarToken(UserDetails u) {

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String role = u.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority())
                .orElse("ROLE_USER");

        Map<String,Object> claims = new HashMap<>();
        claims.put("role", role);

        Date agora = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(u.getUsername())
                .setIssuedAt(agora)
                .setExpiration(new Date(agora.getTime() + expiracao))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extrairUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean valido(String token) {
        try {
            extrairUsername(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrai e-mail (subject)
    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    // Verifica se o token está expirado
    public boolean tokenExpirado(String token) {
        Date expiracao = extrairClaims(token).getExpiration();
        return expiracao.before(new Date());
    }

    // Verifica se token é válido
    public boolean tokenValido(String token, String email) {
        String emailExtraido = extrairEmail(token);
        return (emailExtraido.equals(email) && !tokenExpirado(token));
    }
}