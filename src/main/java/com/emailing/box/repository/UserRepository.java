package com.emailing.box.repository;

import com.emailing.box.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUserName (String userName);
}
