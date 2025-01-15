package com.devalrykemes.forumhub.domain.user;

import com.devalrykemes.forumhub.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();

    public User(UserRequestDto data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    }

    public User(UserResponseDto data) {
        this.id = data.id();
        this.name = data.name();
        this.email = data.email();
        this.profiles = new ArrayList<>();
    }
}