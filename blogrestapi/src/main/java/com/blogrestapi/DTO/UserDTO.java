package com.blogrestapi.DTO;




import com.blogrestapi.Entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;

    @NotEmpty(message = "Required")
    private String username;

    @NotEmpty(message = "Required")
    @Email(message = "Invalid email format")
    private String email;


    // @JsonIgnore // password will not appear in the json field
    @NotEmpty(message = "Required")
    private String password;
    private boolean isEnable;
    private Role role;
}
