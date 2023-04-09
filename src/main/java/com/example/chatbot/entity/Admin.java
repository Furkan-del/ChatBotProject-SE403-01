package com.example.chatbot.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "adminName")
    private String adminName;

    @Column(name = "adminPassword")
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<News> newsList;
}
