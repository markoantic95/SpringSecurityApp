/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.service;

import com.marko.securityApp.model.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Marko
 */
public interface UserService {

    public Boolean isUsernameUnique(String username);

    public Boolean isEmailUnique(String email);

    public List<UserDTO> getAll();
    
//    public List<UserDTO> getUsers();
}
