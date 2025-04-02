package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM comment WHERE topic_id = :topicId;")
    public List<Comment> findAllByTopicId(@Param("topicId")Long topicId);

    @Query(value = "SELECT CASE WHEN COUNT(c.id) > 0 THEN TRUE ELSE FALSE END " +
            "FROM comment c " +
            "JOIN profile p ON c.profile_id = p.id " +
            "JOIN users u ON p.id_user = u.id " +
            "WHERE c.id = :commentId AND u.email = :email", nativeQuery = true)
    Boolean existsByCommentIdAndCreatorEmail(@Param("commentId") Long commentId, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE Comment c SET c.resolved = true WHERE c.id = :commentId")
    void commentResolved(@Param("commentId") Long commentId);
}
