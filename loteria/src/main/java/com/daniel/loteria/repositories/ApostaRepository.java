package com.daniel.loteria.repositories;

import com.daniel.loteria.models.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Integer> {
    //@Query("SELECT * FROM aposta a WHERE a.nome like :nome ORDER BY a.nome ")
    //List<Aposta> findByNome(@Param("nome") String nome);
}
