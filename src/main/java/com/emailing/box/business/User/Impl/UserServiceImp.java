package com.emailing.box.business.User.Impl;

import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.User;
import com.emailing.box.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
