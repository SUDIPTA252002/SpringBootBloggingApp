package com.BlogApp.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserDTO 
{
    private String userId;
    @NotBlank(message="FIRST NAME SHOULD NOT BE BLANK")
    private String firstName;
    
    @NotBlank(message="LAST NAME SHOULD NOT BE BLANK")
    private String lastName;
    @Email(message ="EMAIL IS INVALID")
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private String about;

    private String profilePicture;
}
