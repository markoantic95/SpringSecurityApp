/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.serviceImpl;

import com.marko.securityApp.mapper.UserMapper;
import com.marko.securityApp.model.User;
import com.marko.securityApp.model.dto.UserDTO;
import com.marko.securityApp.repository.UserRepository;
import com.marko.securityApp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marko
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserMapper mapper;

    @Override
    public Boolean isUsernameUnique(String username) {
        Boolean isUnique = userRepository.existsByUsername(username)?false:true;
        return isUnique;
    }

    @Override
    public Boolean isEmailUnique(String email) {
        Boolean isUnique = userRepository.existsByEmail(email)?false:true;
        return isUnique;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return mapper.toDtoModel(users);
    }
//    
//    @Override
//    public List<UserDTO> getUsers() {
//        List<User> users = userRepository.findUsers();
//        return mapper.toDtoModel(users);
//    }
    
    
 
    
}
