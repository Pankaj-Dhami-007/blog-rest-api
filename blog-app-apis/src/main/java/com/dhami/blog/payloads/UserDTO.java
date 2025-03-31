package com.dhami.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {


    private Long id;
    @NotBlank
    @Size(min= 4, message = "Username must be min of 4 character")
    private String name;
    @Email(message = "Email address is Not valid")
    private String email;
    @NotBlank
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
    private String password;
    @NotBlank
    private String about;
}
