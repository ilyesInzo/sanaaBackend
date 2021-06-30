package com.magma.sanaa.services;

import com.magma.sanaa.entities.User;
import com.magma.sanaa.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer id) {

        return userRepository.findById(id);
    }

    public Optional<User> getUser(String email) {

        return userRepository.findUserByEmail(email);
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public boolean deleteUser(Integer id) {

        if (userRepository.findById(id).isPresent()){

            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow( () -> new UsernameNotFoundException("Invalid username or password."));

        return CustomUserDetails.build(user);
    }

}
