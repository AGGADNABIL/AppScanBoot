package com.scann.app.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProviderToken {
	
	private final Long TOKEN_VALIDATION=86400L;
	private final String secretKey="admin";
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims=new HashMap<>();
		claims.put("sub", userDetails.getUsername());
		claims.put("created", new Date());
		return Jwts.builder().setClaims(claims)
				   .setExpiration(generateExpirationDate())
				   .signWith(SignatureAlgorithm.HS512, secretKey)
				   .compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis()+TOKEN_VALIDATION*1000);
	}

}
