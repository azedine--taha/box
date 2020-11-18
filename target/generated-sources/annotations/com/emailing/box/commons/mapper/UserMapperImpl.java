package com.emailing.box.commons.mapper;

import com.emailing.box.entities.Role;
import com.emailing.box.entities.User;
import com.emailing.box.ressources.dto.RoleDto;
import com.emailing.box.ressources.dto.UserDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-18T22:21:52+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDto mapDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setNom( user.getNom() );
        userDto.setEmail( user.getEmail() );
        userDto.setPrenom( user.getPrenom() );
        userDto.setPassword( user.getPassword() );
        userDto.setRoles( roleSetToRoleDtoSet( user.getRoles() ) );

        return userDto;
    }

    @Override
    public User mapEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setNom( userDto.getNom() );
        user.setPrenom( userDto.getPrenom() );
        user.setPassword( userDto.getPassword() );
        user.setEmail( userDto.getEmail() );
        user.setRoles( roleDtoSetToRoleSet( userDto.getRoles() ) );

        return user;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleMapper.mapDto( role ) );
        }

        return set1;
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleMapper.mapEntity( roleDto ) );
        }

        return set1;
    }
}
