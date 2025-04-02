package com.devalrykemes.forumhub.domain.comment;


import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menssage")
    private String menssage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "solved")
    private Boolean resolved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile creator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Topic_Id")
    private Topic topic;

    public Comment(CommentRequestDto data) {
        this.menssage = data.menssage();
        this.createdAt = LocalDateTime.now();
        this.resolved = false;
    }
}
