package com.emailing.box.business.user.Impl;

import com.emailing.box.business.user.IControleActionClient;
import com.emailing.box.business.user.IUserService;
import com.emailing.box.entities.User;
import com.emailing.box.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IControleActionClient controleActionClient;


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @PreAuthorize("@controleACtionClient.hasNonLegacyAction('admin')")
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
