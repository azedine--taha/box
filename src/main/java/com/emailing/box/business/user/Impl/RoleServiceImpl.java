package com.emailing.box.business.user.Impl;

import com.emailing.box.business.user.IRoleService;
import com.emailing.box.entities.Role;
import com.emailing.box.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
       roleRepository.save(role) ;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return null;
    }
}
