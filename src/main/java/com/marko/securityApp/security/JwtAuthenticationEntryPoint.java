/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marko
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    
    // This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
    @Override
    public void commence(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", ae.getMessage());
        hsr1.sendError(hsr1.SC_UNAUTHORIZED, ae.getMessage());
    }
    
}

//Explanation : A servlet is simply a class which responds to a particular type of network request - most commonly an HTTP request.
// HttpServletResponse -  provide HTTP-specific functionality in sending a response. For example, it has methods to access HTTP headers and cookies