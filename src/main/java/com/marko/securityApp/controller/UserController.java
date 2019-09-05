/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.controller;

import com.marko.securityApp.model.dto.UserDTO;
import com.marko.securityApp.security.CurrentUser;
import com.marko.securityApp.security.UserPrincipal;
import com.marko.securityApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marko
 */
@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/user/current")
    @PreAuthorize("hasRole('USER')")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal user){
        return new UserDTO(user.getName(), user.getUsername(), user.getEmail(), user.getPassword());
    }
    
    @GetMapping("/user/isUsernameUnique")
    public Boolean isUsernameUnique(@RequestParam(value = "username") String username){
        return userService.isUsernameUnique(username);
    }
    
    @GetMapping("/user/isEmailUnique")
    public Boolean isEmailUnique(@RequestParam(value = "email") String email){
        return userService.isEmailUnique(email);
    }
}
