package com.validationproject.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    @NotBlank(message = "UserName cannot be blank")
    private String username;
    @Pattern(regexp ="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid message")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3,max=12,message = "Password should be less than 12 and greater than 3 character")
    private String password;

    @AssertTrue(message = "Must agree term and condition")
    private boolean agreed;

    
    //Default constructor
    public User(){
        super();
    }
    //Parameterized constructor
    public User(String username, String email, String password , Boolean agreed) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.agreed=agreed;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAgreed(){
        return agreed;
    }
    public void setAgreed(Boolean agreed){
        this.agreed=agreed;
    }
    public String toString(){
        return "User[username= "+username+",\n\t email= "+ email+",\n\t password= "+password+",\n\t Agreed= "+agreed +"\n]";
    }

    
}
