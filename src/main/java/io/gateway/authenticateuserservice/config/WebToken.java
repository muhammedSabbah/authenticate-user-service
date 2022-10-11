package io.gateway.authenticateuserservice.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class WebToken {
	private static final String SECRET = "secret";
	private static final String ROLES = "ROLES";

	
	private static Algorithm algorithm() {
		return Algorithm.HMAC256(SECRET.getBytes());
	}
	
	public static String generateToken(User user, TOKEN_TYPE token) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(ROLES, user.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return generateToken(claims, user.getUsername(), token);
	}
	
	private static String generateToken(Map<String, Object> claims, String subject, TOKEN_TYPE token) {
		final Date createdDate = new Date(System.currentTimeMillis());
		return JWT.create()
				.withClaim(ROLES, claims)
				.withSubject(subject)
				.withIssuedAt(createdDate)
				.withExpiresAt(exprirationDate(createdDate, token))
				.sign(algorithm());
	}
	
	private static Date exprirationDate(Date createdDate, TOKEN_TYPE token) {
		return new Date(createdDate.getTime() + token.expiration());
	}
	
	public static Boolean isTokenExpired(String token) {
		final Date expiresAt = JWT.decode(token).getExpiresAt();
		return expiresAt.before(new Date(System.currentTimeMillis()));
	}
	
}
