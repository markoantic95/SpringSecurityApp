/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.config;

import com.marko.securityApp.security.CustomUserDetailsService;
import com.marko.securityApp.security.JwtAuthenticationEntryPoint;
import com.marko.securityApp.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Marko
 */
@Configuration
@EnableWebSecurity // - enable web security in a project
@EnableGlobalMethodSecurity( //- enable method level security. It enables some annotations listed down below
        securedEnabled = true, //@Secured annotation for protecting controller/service methods
        jsr250Enabled = true, //@RolesAllowed annotation, for example createUser() method is only allowed for admin role
        prePostEnabled = true) // @PreAuthorize, @PostAuthorize

//WebSecurityConfigurerAdapter provides default security configurations and allows other classes to extend it and customize the security configurations by overriding its methods
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // to  load users details 
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //This class is used to return a 401 unauthorized error to clients that try 
    //to access a protected resource without proper authentication. It implements Spring Security’s AuthenticationEntryPoint interface.
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //reads JWT authentication token from the Authorization header of all the requests
    //validates the token
    //loads the user details associated with that token.
    //Sets the user details in Spring Security’s SecurityContext.
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //?  matches one character
    //*  matches zero or more characters
    //** matches zero or more directories in a path
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // Add our custom JWT security filter
        // addFilterBefore(filter, class) – adds a filter - jwtAuthenticationFilter before the position of the specified filter class - UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
