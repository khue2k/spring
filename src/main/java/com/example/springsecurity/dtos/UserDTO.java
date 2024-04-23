package com.example.springsecurity.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    @NotBlank(message = "Email is required ")
    private String email;

    @Size(min = 8, max = 20, message = "Invalid password , password must be at least 6 character !")
    private String password;

    private String firstName;

    private String lastName;
}
