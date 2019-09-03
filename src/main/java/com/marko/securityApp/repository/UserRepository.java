/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.repository;

import com.marko.securityApp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marko
 */

//@Repository’s job is to catch persistence specific exceptions and rethrow them as one of Spring’s unified unchecked exception.
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    Optional<User> findByUsername(String username);
    
    List<User> findById(List<Long> userIds);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);

}
