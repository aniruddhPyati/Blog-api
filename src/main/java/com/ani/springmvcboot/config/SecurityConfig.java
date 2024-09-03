package com.ani.springmvcboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ani.springmvcboot.security.JwtAuthenticationEntryPoint;
import com.ani.springmvcboot.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled =true)
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
	 @Autowired
	    private JwtAuthenticationEntryPoint point;
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    
	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    
	   
	    
	    
	    
	    @SuppressWarnings("deprecation")
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http.csrf(csrf-> csrf.disable())
	    	    .cors(cors -> cors.disable())
	    	    .authorizeHttpRequests(auth -> auth
	    	    		.requestMatchers("/api/auth/**")
	    	    		.permitAll()
	    	    		.requestMatchers("/api/users/").permitAll()
	    	    		.requestMatchers("/v3/api-docs").permitAll()
	    	    		.requestMatchers("/swagger-ui/**").permitAll()
	    	    		.requestMatchers("/swagger-resources/**").permitAll()
	    	    		.requestMatchers("/api/**")
	    	    		.authenticated()
	    	    		.requestMatchers("/error").permitAll()
	    	    		.anyRequest()
	    	    		.authenticated()
	    	    		).exceptionHandling(ex -> ex.authenticationEntryPoint(point)
	    	    				).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    	    ;
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    	
	    	return http.build();
	    	    
	    }
	    
	    @Bean
	    public DaoAuthenticationProvider doDaoAuthenticationProvider() {
	    	
	    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
	    	return daoAuthenticationProvider;
	    }
}
