package com.devalrykemes.forumhub.domain.topic;

import com.devalrykemes.forumhub.domain.comment.Comment;
import com.devalrykemes.forumhub.domain.course.Course;
import jakarta.persistence.*;
import lombok.*;

import com.devalrykemes.forumhub.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "menssage")
    private String menssage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany
    private List<Comment> comments;


}
