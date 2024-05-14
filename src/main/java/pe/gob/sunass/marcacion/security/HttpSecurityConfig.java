package pe.gob.sunass.marcacion.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;
import pe.gob.sunass.marcacion.constant.AppConstant;
import pe.gob.sunass.marcacion.security.jwt.JWTAuthenticationEntryPoint;
import pe.gob.sunass.marcacion.security.jwt.JWTAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
	
    @Autowired
    private JWTAuthenticationFilter authenticationFilter;
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	return http
			.csrf(csrf -> csrf.disable())
			.httpBasic(it -> it.disable() )
			.authorizeHttpRequests( auth -> {
				auth.requestMatchers("/").permitAll();
				auth.requestMatchers("/api/logout/**").permitAll();
				auth.requestMatchers("/api/auth/**").permitAll();
				// auth.requestMatchers("/api/maestro/**").hasAnyRole(AppConstant.ROL_ADMIN, AppConstant.ROL_USER);
				// auth.requestMatchers("/api/log/**").hasAnyRole(AppConstant.ROL_ADMIN, AppConstant.ROL_USER);
				// auth.requestMatchers("/api/marcacion/**").hasAnyRole(AppConstant.ROL_ADMIN, AppConstant.ROL_USER);
				auth.anyRequest().authenticated();
			})
			.exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(authenticationFilter, UserAuthenticationFilter.class)
			.exceptionHandling( ex -> ex.authenticationEntryPoint(new JWTAuthenticationEntryPoint() ))
			.build();
	}
}
