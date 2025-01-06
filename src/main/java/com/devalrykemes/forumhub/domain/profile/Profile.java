package com.devalrykemes.forumhub.domain.profile;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;
}
