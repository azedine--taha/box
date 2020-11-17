package com.emailing.box.business.user;

import com.emailing.box.entities.User;
import com.emailing.box.ressources.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto findByUserName(String userName);

    UserDto findByEmail (String email);

    List<UserDto> getAll();

    void save(UserDto user);
}
