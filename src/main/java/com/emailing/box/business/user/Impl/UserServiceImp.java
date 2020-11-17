package com.emailing.box.business.user.Impl;

import com.emailing.box.business.user.IControleActionClient;
import com.emailing.box.business.user.IUserService;
import com.emailing.box.commons.mapper.UserMapper;
import com.emailing.box.entities.User;
import com.emailing.box.repository.UserRepository;
import com.emailing.box.ressources.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserServiceImp implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IControleActionClient controleActionClient;

    @Autowired
    UserMapper userMapper;


    @Override
    public UserDto findByUserName(String userName) {
        return userMapper.mapDto(userRepository.findByUserName(userName)) ;
    }

    @Override
    public UserDto findByEmail(String email) {

        User byEmail = userRepository.findByEmail(email);
        return userMapper.mapDto(byEmail);
    }

    @Override
    @PreAuthorize("@controleACtionClient.hasNonLegacyAction('admin')")
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(u -> {
            return userMapper.mapDto(u);
        }).collect(Collectors.toList());
    }

    @Override
    public void save(UserDto userDto) {
        userRepository.save(userMapper.mapEntity(userDto));
    }
}
