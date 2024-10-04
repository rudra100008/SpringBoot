package com.blogrestapi;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogrestapi.Config.AppConstant;
import com.blogrestapi.Dao.RoleDao;
import com.blogrestapi.Entity.Role;

@SpringBootApplication
public class BlogrestapiApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(BlogrestapiApplication.class, args);
    }
    @Override
    public void run(String... args) {
        System.out.println(this.passwordEncoder.encode("ashum123"));
        try {
            Role role=new Role();
            role.setId(AppConstant.ADMIN_USER);
            role.setName("ROLE_ADMIN");
            Role role1=new Role();
            role1.setId(AppConstant.NORMAL_USER);
            role1.setName("ROLE_USER");
            List<Role> listRoles= List.of(role,role1);
            roleDao.saveAll(listRoles);
        } catch (Exception e) {
           System.out.println(e.getLocalizedMessage());
        }
       

    }
}
