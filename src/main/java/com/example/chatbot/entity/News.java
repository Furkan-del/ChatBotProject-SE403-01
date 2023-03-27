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

    @Column(name = "newsType")
    private String newsType;

    @Column(name = "newsDate")
    private Date newsDate;
    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;
}
