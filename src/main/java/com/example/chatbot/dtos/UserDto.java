package com.example.chatbot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserDto {

    @NotEmpty
    @NotNull
    private String userName;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private String matchingPassword;

    @NotEmpty
    @NotNull
    private String email;


}
