package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "commentName")
    private String commentName;

    @Column(name = "commentType")
    private String commentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}