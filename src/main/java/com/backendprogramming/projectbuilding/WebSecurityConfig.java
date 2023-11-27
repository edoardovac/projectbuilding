package com.backendprogramming.projectbuilding;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.backendprogramming.projectbuilding.web.UserDetailServiceImpl;

//WebSecurityConfig class contains a method configure(HttpSecurity)
//that defines which URL paths are secured and the path for login form

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(antMatcher("/signup")).permitAll().requestMatchers(antMatcher("/savenewuser"))
				.permitAll().anyRequest().authenticated())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions // for h2console
						.disable()))

				.formLogin(formlogin -> formlogin.loginPage("/login").defaultSuccessUrl("/buildings", true).permitAll())
				.logout(logout -> logout.permitAll());

		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}