package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.topic.Topic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(t.id) > 0 THEN TRUE ELSE FALSE END " +
            "FROM topic t " +
            "JOIN profile p ON t.profile_id = p.id " +
            "JOIN users u ON p.id_user = u.id " +
            "WHERE t.id = :topicId AND u.email = :email", nativeQuery = true)
    Boolean existsByTopicIdAndCreatorEmail(@Param("topicId") Long topicId, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE Topic t SET t.status = com.devalrykemes.forumhub.domain.topic.TopicStatus.SOLVED WHERE t.id = :topicId")
    void topicIsSolved(@Param("topicId") Long topicId);
}
