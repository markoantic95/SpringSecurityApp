/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.config;

import com.marko.securityApp.exception.AppException;
import com.marko.securityApp.model.Privilege;
import com.marko.securityApp.model.PrivilegeName;
import com.marko.securityApp.model.Role;
import com.marko.securityApp.model.RoleName;
import com.marko.securityApp.repository.PrivilegeRepository;
import com.marko.securityApp.repository.RoleRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marko
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        if (alreadySetup) {
            return;
        }

        final Privilege readPrivilege = findPrivilege(PrivilegeName.READ_PRIVILEGE);
        final Privilege writePrivilege = findPrivilege(PrivilegeName.WRITE_PRIVILEGE);

        final Set<Privilege> adminPrivileges = new HashSet<Privilege>(Arrays.asList(readPrivilege, writePrivilege));
        final Set<Privilege> userPrivileges = new HashSet<Privilege>(Arrays.asList(readPrivilege));

        setPrivilegesToRole(RoleName.ROLE_ADMIN, adminPrivileges);
        setPrivilegesToRole(RoleName.ROLE_USER, userPrivileges);

        alreadySetup = true;
    }

    private Privilege findPrivilege(PrivilegeName privilegeName) {
        Privilege privilege = privilegeRepository.findByName(privilegeName)
                .orElseThrow(() -> new AppException("Privilege not set."));
        return privilege;
    }

    private void setPrivilegesToRole(RoleName roleName, Set<Privilege> adminPrivileges) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new AppException("Role not set."));
        role.setPrivileges(adminPrivileges);
        role = roleRepository.save(role);
    }

}
