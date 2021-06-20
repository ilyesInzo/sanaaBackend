package com.magma.sanaa.repositories;

import com.magma.sanaa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email like %?1")
    Optional<User> findUserByEmail(String chars);

}