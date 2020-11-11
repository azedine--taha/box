package com.emailing.box.business.User;

import com.emailing.box.entities.Role;

public interface IRoleService {

    void saveRole(Role role);

    Role getRoleByName(String roleName);
}
