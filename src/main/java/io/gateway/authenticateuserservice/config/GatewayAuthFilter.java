package io.gateway.authenticateuserservice.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.gateway.authenticateuserservice.config.TOKEN_TYPE.*;

public class GatewayAuthFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	public GatewayAuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Username is " + username);
		System.out.println("password is " + password);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return this.authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		Map<String, String> tokens = new HashMap<>();
		tokens.put(ACCESS_TOKEN.value(), WebToken.generateToken(user, ACCESS_TOKEN));
		tokens.put(REFRESH_TOKEN.value(), WebToken.generateToken(user, REFRESH_TOKEN));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

	
}
