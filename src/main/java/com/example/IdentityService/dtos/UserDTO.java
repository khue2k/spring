package com.example.IdentityService.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    @NotBlank(message = "Email is required ")
    private String email;

    @NotBlank(message = "Password is required ")
    private String password;

    private String firstName;

    private String lastName;
}
