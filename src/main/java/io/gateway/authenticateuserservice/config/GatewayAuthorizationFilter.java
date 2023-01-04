package io.gateway.authenticateuserservice.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


public class GatewayAuthorizationFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Authentication Request For '{}'", request.getRequestURL());
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		String token = null;
		String username = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring("Bearer ".length());
			try {
				username = WebToken.getUsernameFromToken(token);
			} catch (IllegalArgumentException illegal) {
				logger.error("JWT_TOKEN_Illegal_Argument_Exception", illegal.getMessage());
			} catch (Exception ex) {
				logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", ex.getMessage());
			}
		} else {
			logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
		}

		logger.info("JWT_TOKEN_USERNAME_VALUE '{}'", username);
		if (username != null) {
			String[] roles = WebToken.getRoles(token);
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			Arrays.asList(roles).stream().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role));
			});
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}

}
