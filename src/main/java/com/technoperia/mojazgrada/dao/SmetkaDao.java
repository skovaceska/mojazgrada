package com.technoperia.mojazgrada.dao;

import com.technoperia.mojazgrada.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmetkaDao extends JpaRepository<Smetka, Long> {
    Smetka findById(Long id);
    List<Smetka> findByStan(Stan stan);
    List<Smetka> findByUser(User user);
}
