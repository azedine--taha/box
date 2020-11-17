package com.emailing.box.commons.mapper;


import com.emailing.box.entities.User;
import com.emailing.box.ressources.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {
    UserDto mapDto(User user);
    User  mapEntity (UserDto userDto);
}
