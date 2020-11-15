package com.emailing.box.business.user;

import com.emailing.box.entities.User;

import java.util.List;

public interface IUserService {

    User findByUserName(String userName);

    User findByEmail (String email);

    List<User> getAll();

    void save(User user);
}
