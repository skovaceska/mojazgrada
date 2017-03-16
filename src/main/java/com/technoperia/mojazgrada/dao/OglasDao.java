package com.technoperia.mojazgrada.dao;

import com.technoperia.mojazgrada.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OglasDao extends JpaRepository<Oglas, Long> {
    Oglas findById(Long id);
    List<Oglas> findByStan(Stan stan);
}