/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 * @author Marko
 */
// implementation of AuditorAware interface - it doesnt return name(which is default behavior valjda) but id of principal
@Configuration
@EnableJpaAuditing  // we want to enable auditing via annotation configuration
public class AuditingConfig {
    // In order to configure the app to use AuditorAwareImpl to look up the current principal, 
 //   declare a bean of AuditorAware type initialized with an instance of AuditorAwareImpl and 
    //specify the bean's name as the auditorAwareRef parameter's value in @EnableJpaAuditing:
}
