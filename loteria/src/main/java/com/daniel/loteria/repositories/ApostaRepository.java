package com.daniel.loteria.repositories;

import com.daniel.loteria.models.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Integer> {
}
