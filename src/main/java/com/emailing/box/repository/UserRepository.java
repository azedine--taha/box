package com.emailing.box.repository;

import com.emailing.box.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {

    //@Query("select u from User u where u.email = ?1")
    @Query(value = "SELECT * FROM Utilisateur WHERE EMAIL = ?1", nativeQuery = true)
    User findByUsername (String email);
}
