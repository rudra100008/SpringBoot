package com.blogrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogrestapiApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder; // Change to PasswordEncoder

    public static void main(String[] args) {
        SpringApplication.run(BlogrestapiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) {
        System.out.println(this.passwordEncoder.encode("ashum123"));
    }
}
