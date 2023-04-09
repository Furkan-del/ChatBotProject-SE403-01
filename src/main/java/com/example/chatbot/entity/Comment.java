package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "comment")
    private String comment;

    @Column(name = "commentType")
    private String commentType;

    @Column(name = "date")
    private String dates;

    @Column(name = "userName")
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}