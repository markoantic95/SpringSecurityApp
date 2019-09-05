/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.mapper;

import com.marko.securityApp.model.User;
import com.marko.securityApp.model.dto.UserDTO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author Marko
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDTO toDtoModel(User user);
    
    List<UserDTO> toDtoModel(List<User> users);
    
    User toDataModel(UserDTO user);
    
}
