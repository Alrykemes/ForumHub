package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.profile.Profile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE profile SET name = :name WHERE id = :id AND id_user = :idUser")
    public void updateProfileById(@Param("id") Long id, @Param("name") String name, @Param("idUser") UUID idUser);

    @Query(value = "SELECT CASE WHEN COUNT(p.id) > 0 THEN TRUE ELSE FALSE END " +
            "FROM profile p " +
            "JOIN users u ON p.id_user = u.id " +
            "WHERE p.id = :profileId AND u.email = :email", nativeQuery = true)
    Boolean existsByProfileIdAndCreatorEmail(@Param("profileId") Long profileId, @Param("email") String email);
}
