package com.emailing.box.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Utilisateur")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(name = "USER_NAME",unique = true)
    private String username;

    private String nom;

    private String prenom;

    @Column(name = "MOT_PASSE",nullable = false)
    private String password;

    @Column(name = "EMAIL",unique = true,nullable = false)
    private String email;
}
