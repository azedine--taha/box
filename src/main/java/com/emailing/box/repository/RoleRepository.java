package com.emailing.box.repository;

import com.emailing.box.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Role findRoleByName (String roleName);
}
