package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "News")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "newsContent")
    private String newsContent;

    @Column(name = "NewsHeader")
    private String newsHeader;

    @Column(name = "imageData")
    @Lob
    private String photo;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user; */

    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL)
    private List<Comment> commentList=new ArrayList<>();

}
