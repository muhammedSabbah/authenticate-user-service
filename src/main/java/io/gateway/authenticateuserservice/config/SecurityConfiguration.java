package io.gateway.authenticateuserservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.gateway.authenticateuserservice.utils.USER_ROLE;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetails;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.version}")
	private String appVersion;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		GatewayAuthFilter authFilter = new GatewayAuthFilter(authenticationManagerBean());
		authFilter.setFilterProcessesUrl(appVersion + "/login");
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(new JwtUnAuthorizedResponseAuthenticationEntryPoint());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(appVersion + "/login/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, appVersion + "/users/**").hasAnyAuthority(USER_ROLE.ROLE_ADMIN.value());
		http.authorizeRequests().antMatchers(HttpMethod.POST, appVersion + "/user/save/**").hasAnyAuthority(USER_ROLE.ROLE_SUPER_ADMIN.value());
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(authFilter);
		http.addFilterBefore(new GatewayAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
}
