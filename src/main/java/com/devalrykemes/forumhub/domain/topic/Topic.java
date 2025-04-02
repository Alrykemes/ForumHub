package com.devalrykemes.forumhub.domain.topic;

import com.devalrykemes.forumhub.domain.comment.Comment;
import com.devalrykemes.forumhub.domain.course.Course;
import com.devalrykemes.forumhub.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "menssage")
    private String menssage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TopicStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Topic(TopicRequestDto data) {
        this.title = data.title();
        this.menssage = data.menssage();
        this.createdAt = LocalDateTime.now();
        this.status = TopicStatus.INPROGRESS;
    }

}
