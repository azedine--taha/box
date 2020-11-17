package com.emailing.box.commons.mapper;

import com.emailing.box.entities.Role;
import com.emailing.box.ressources.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleDto mapDto(Role role);
    Role  mapEntity (RoleDto roleDto);
}
