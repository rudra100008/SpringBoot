package com.blogrestapi.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User  implements UserDetails{
    @MongoId
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isEnable;
    @DBRef
    private List<Post> post = new ArrayList<>();
    @DBRef
    private Set<Role> role=new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return  this.role.stream().map(role->new SimpleGrantedAuthority(role.getName())).toList();
       
    }
    @Override
    public String getPassword() {
        return password; // Return the password
    }

    @Override
    public String getUsername() {
        return username; // Return the username
    }
}
