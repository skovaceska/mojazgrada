package com.technoperia.mojazgrada.dao;


import com.technoperia.mojazgrada.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(Long id);
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByStan(Stan stan);

    @Query(value = "select u from User u")
    List<User> findAll();
}