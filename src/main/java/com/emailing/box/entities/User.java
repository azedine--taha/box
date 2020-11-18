package com.emailing.box.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Utilisateur")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "USER_NAME",unique = true)
    private String username;

    private String nom;

    private String prenom;

    @Column(name = "MOT_PASSE",nullable = false)
    private String password;

    @Column(name = "EMAIL",unique = true,nullable = false)
    private String email;


    @ManyToMany
    @JoinTable(name = "USER_ROLES",
                joinColumns = @JoinColumn(name = "ID_USER"),
                inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private Set<Role> roles = new HashSet<Role>();
}
