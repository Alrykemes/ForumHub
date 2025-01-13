package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END\n" +
            "FROM users\n" +
            "WHERE email = :email;")
    public Boolean existsByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users SET name = :name, email = :email, password = :password " +
            "WHERE id = :id;")
    public void updateUser(@Param("id") UUID id, @Param("name") String name, @Param("email") String email, @Param("password") String password);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE id = :id;")
    public Optional<User> findById(@Param("id") UUID uuid);
}