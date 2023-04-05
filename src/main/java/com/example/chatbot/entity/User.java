package com.example.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message = "Name is required")
    @Column(name = "username")
    private String userName;


    @Column(name = "email")
    @Email(message = "Email is invalid")
    private String email;

    @Column(name = "password")
    @Size(min = 6,message = "Password must be at least 6 characters long")
    private String password;


    @Column(name="phoneNumber")
    private Integer phoneNumber;
}
