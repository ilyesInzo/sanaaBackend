package com.magma.sanaa;

import com.magma.sanaa.entities.User;
import com.magma.sanaa.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SanaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanaaApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("abcd");

            User user = new User();
            user.setPassword(encodedPassword);
            user.setEmail("inzodialo@gmail.com");
            userService.saveUser(user);

        };
    }

}
