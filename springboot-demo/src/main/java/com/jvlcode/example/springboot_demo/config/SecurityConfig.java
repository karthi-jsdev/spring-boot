package com.jvlcode.example.springboot_demo.config;

import com.jvlcode.example.springboot_demo.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
				.authorizeHttpRequests(authz ->
						authz
								.requestMatchers(
										HttpMethod.POST,
										"/api/users",
										"/api/users/",
										"/auth/login"
								).permitAll()
								.requestMatchers("/api/users/**").authenticated()
								.requestMatchers("/").permitAll()
								.anyRequest().permitAll()
				)
				.formLogin(form -> form
						.permitAll()
						.defaultSuccessUrl("/dashboard")
				)
				.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider authProvider =
				new DaoAuthenticationProvider(userDetailsService);

		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(List.of(provider));
	}
}