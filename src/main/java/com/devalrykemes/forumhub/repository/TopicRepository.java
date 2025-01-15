package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
