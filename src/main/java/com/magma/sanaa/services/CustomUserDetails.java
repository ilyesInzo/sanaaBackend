package com.magma.sanaa.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magma.sanaa.Utils.ConstantsHelper;
import com.magma.sanaa.entities.RolePermission;
import com.magma.sanaa.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private Collection authorities;

    public CustomUserDetails(Integer id, String firstName,
                             String lastName, String email, String password,
                             Collection authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetails build(User user) {

        return new CustomUserDetails(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                getAuthority(user)
        );
    }

    private static List<SimpleGrantedAuthority> getAuthority(User currentUser) {

        return currentUser.getRole().getRolePermissions().stream()
                .map( rp ->
                        fromPermissionToAuthorities(rp)
                ).flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<SimpleGrantedAuthority> fromPermissionToAuthorities(RolePermission rolePermission){
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

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(id, user.id);
    }
}
