package com.technoperia.mojazgrada.dao;

import com.technoperia.mojazgrada.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StanDao extends JpaRepository<Stan, Long> {
    Stan findById(Long id);
    Stan findBySifra(String sifra);
}
