package com.technoperia.mojazgrada.dao;


import com.technoperia.mojazgrada.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(Long id);
}