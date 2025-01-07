package com.devalrykemes.forumhub.domain.course;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;


}
