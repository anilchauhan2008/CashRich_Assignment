package com.anilchauhan.cashrich_assignment.secutiry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

	 @Value("${spring.security.jwt.secret-key}")
	    private String jwtSecret;

	    @Value("${spring.security.jwt.expiration}")
	    private int jwtExpiration;

	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }

	    public String getUsernameFromJwt(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
}