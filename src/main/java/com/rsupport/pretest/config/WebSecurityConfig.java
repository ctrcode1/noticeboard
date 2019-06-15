package com.rsupport.pretest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired private SimpleAuthenticationSuccessHandler successHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf()
                .ignoringAntMatchers("/h2-console/**")	    
                .and()
            .headers().frameOptions().disable()
            	.and()
            .formLogin()
            	.successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
        List<UserDetails> users = new ArrayList<>();
        users.add(User.withUsername("user1") 
                .password(encoder.encode("password1")) 
                .roles("USER") 
                .build());
        users.add(User.withUsername("user2") 
                .password(encoder.encode("password2")) 
                .roles("USER") 
                .build());
        return new InMemoryUserDetailsManager(users); 
    }
}