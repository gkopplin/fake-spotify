package com.ga.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {	
	    @SuppressWarnings("deprecation")
		User.UserBuilder users = User.withDefaultPasswordEncoder();
	    
	    auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/user/signup/**", "/user/login/**").permitAll()
	        .antMatchers("/user/**", "/song/**").authenticated()
	        .antMatchers("/role/**").hasRole("ADMIN")
//	        delete user
	        .and()
	        .httpBasic()
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
//	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
