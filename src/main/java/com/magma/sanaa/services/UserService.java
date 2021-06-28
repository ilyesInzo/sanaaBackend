package com.magma.sanaa.services;

import com.magma.sanaa.Utils.ConstantsHelper;
import com.magma.sanaa.entities.RolePermission;
import com.magma.sanaa.entities.User;
import com.magma.sanaa.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(userEmail);

        if(!user.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User currentUser = user.get();

        return new org.springframework.security.core.userdetails.User(currentUser.getEmail(), currentUser.getPassword(), getAuthority(currentUser));
    }

    private List<SimpleGrantedAuthority> getAuthority(User currentUser) {

        return currentUser.getRole().getRolePermissions().stream()
                .map( rp ->
                     fromPermissionToAuthorities(rp)
                ).flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> fromPermissionToAuthorities(RolePermission rolePermission){
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        if (rolePermission.isCreate()) {
            simpleAuthorities.add(new SimpleGrantedAuthority(ConstantsHelper.ROLE + ConstantsHelper.UNDERSCORE + rolePermission.getPermission().getName() + ConstantsHelper.UNDERSCORE + ConstantsHelper.CREATE));
        }
        if (rolePermission.isWrite()) {
            simpleAuthorities.add(new SimpleGrantedAuthority(ConstantsHelper.ROLE + ConstantsHelper.UNDERSCORE + rolePermission.getPermission().getName() + ConstantsHelper.UNDERSCORE + ConstantsHelper.WRITE));
        }
        if (rolePermission.isRead()) {
            simpleAuthorities.add(new SimpleGrantedAuthority(ConstantsHelper.ROLE + ConstantsHelper.UNDERSCORE + rolePermission.getPermission().getName() + ConstantsHelper.UNDERSCORE + ConstantsHelper.READ));
        }
        if (rolePermission.isDelete()) {
            simpleAuthorities.add(new SimpleGrantedAuthority(ConstantsHelper.ROLE + ConstantsHelper.UNDERSCORE + rolePermission.getPermission().getName() + ConstantsHelper.UNDERSCORE + ConstantsHelper.DELETE));
        }
        return simpleAuthorities;
    }

}
