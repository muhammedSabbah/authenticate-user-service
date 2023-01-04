package io.gateway.authenticateuserservice.config;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class WebToken {
	private static final String SECRET = "secret";
	private static final String ROLES = "ROLES";

	
	private static Algorithm algorithm() {
		return Algorithm.HMAC256(SECRET.getBytes());
	}
	
	public static String generateToken(User user, TOKEN_TYPE token) {
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return generateToken(roles, user.getUsername(), token);
	}
	
	private static String generateToken(List<String> roles, String subject, TOKEN_TYPE token) {
		final Date createdDate = new Date(System.currentTimeMillis());
		return JWT.create()
				.withClaim(ROLES, roles)
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
	
	private static DecodedJWT generateJWT(String token) {
		JWTVerifier verifier = JWT.require(algorithm()).build();
		return verifier.verify(token);
	}
	
	public static String getUsernameFromToken(String token) {
		return generateJWT(token).getSubject();
	}
	
	public static String[] getRoles(String token) {
		String[] roles = generateJWT(token).getClaim(ROLES).asArray(String.class);
		return roles;
	}
}
