package com.emailing.box.commons.mapper;

import com.emailing.box.entities.Role;
import com.emailing.box.ressources.dto.RoleDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-18T22:21:52+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto mapDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setRolename( role.getRolename() );

        return roleDto;
    }

    @Override
    public Role mapEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setRolename( roleDto.getRolename() );

        return role;
    }
}
