package com.emailing.box.ressources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String nom;
    private String email;
    private String prenom;
    private String password;
    private Set<RoleDto> roles;
}
