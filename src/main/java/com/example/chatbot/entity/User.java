package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username",unique = true)
    private String userName;
    @Column(name = "email")
    @Email(message = "Email is invalid")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private Integer phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<Comment>commentList;

    @Column(name = "roles")
    private String role;

}
