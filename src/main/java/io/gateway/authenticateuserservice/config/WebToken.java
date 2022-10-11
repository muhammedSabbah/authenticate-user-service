package io.gateway.authenticateuserservice.config;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class WebToken {
	
	private static final String secret = "secret";

	
	private static Algorithm algorithm() {
		return Algorithm.HMAC256(secret.getBytes());
	}
	
	public static String generateWebToken(User user) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(exprirationDate())
				.withClaim("roles", 
						user.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm());
	}
	
	private static Date exprirationDate() {
		int duration = 10 * 60 * 1000;
		return new Date(System.currentTimeMillis() + duration);
	}
	
}
