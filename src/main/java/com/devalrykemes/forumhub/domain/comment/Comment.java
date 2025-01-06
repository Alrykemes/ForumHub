package com.devalrykemes.forumhub.domain.comment;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;


}
