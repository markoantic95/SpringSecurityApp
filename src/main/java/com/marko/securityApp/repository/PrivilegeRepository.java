/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.repository;

import com.marko.securityApp.model.Privilege;
import com.marko.securityApp.model.PrivilegeName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marko
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

    Optional<Privilege> findByName(PrivilegeName privilegeName);

}
