package com.insta.api.gateway.config;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "2fb975170ff0bdf763284e08c4bce14d2e00c761503413dcceb93e7409953d1a";

	public void validateToken(final String token) {
		Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
