package com.blogrestapi.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;

    @NotEmpty(message = "Required")
    private String username;

    @NotEmpty(message = "Required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Required")
    @Size(min = 3, max = 16, message = "Password should be between 3 and 16 characters")
    private String password;

    private boolean isEnable;
}
