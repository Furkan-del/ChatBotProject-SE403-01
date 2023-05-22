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

    public static double rate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "commentType")
    private String commentType;
    @Column(name = "date")
    private String dates;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;


}