package com.blogrestapi;


<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

<<<<<<< HEAD
import com.blogrestapi.Config.AppConstant;
import com.blogrestapi.Dao.RoleDao;
import com.blogrestapi.Entity.Role;

=======
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
@SpringBootApplication
public class BlogrestapiApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
<<<<<<< HEAD
    @Autowired
    private RoleDao roleDao;
=======
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3

    public static void main(String[] args) {
        SpringApplication.run(BlogrestapiApplication.class, args);
    }
    @Override
    public void run(String... args) {
        System.out.println(this.passwordEncoder.encode("ashum123"));
<<<<<<< HEAD
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
       

=======
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
    }
}
