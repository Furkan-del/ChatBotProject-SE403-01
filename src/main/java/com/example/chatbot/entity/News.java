package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "newsName")
    private String newsName;

    @Column(name = "newsHeader")
    private String newsHeader;

    @Column(name = "newsType")
    private Date newsType;

    @Column(name = "imagedata",columnDefinition = "MEDIUMBLOB")
    @Lob
    private String photo;

    @ManyToOne
    @JoinTable(name = "user_id")
    private User user;
}
