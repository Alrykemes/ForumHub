package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END\n" +
            "FROM users\n" +
            "WHERE email = :email;")
    public Boolean existsByEmail(@Param("email") String email);
}