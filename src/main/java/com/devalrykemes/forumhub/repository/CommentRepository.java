package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
