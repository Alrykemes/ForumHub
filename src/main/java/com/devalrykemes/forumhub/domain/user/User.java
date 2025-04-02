package com.devalrykemes.forumhub.domain.user;

import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.infra.dto.RegisterRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
@Entity(name = "user")
public class User implements UserDetails {

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

    public User(UserResponseDto data) {
        this.id = data.id();
        this.name = data.name();
        this.profiles = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}