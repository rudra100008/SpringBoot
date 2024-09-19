package com.blogrestapi.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isEnable;
}
