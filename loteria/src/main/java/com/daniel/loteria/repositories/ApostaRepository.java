package com.daniel.loteria.repositories;

import com.daniel.loteria.models.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Integer> {

    List<Aposta> findByNome(String nome);

    @Query(value = "SELECT * FROM aposta a, pessoa p " +
                        "WHERE a.id_pessoa = p.id " +
                            "AND a.nome = ?1 " +
                                "AND  p.email = ?2 " +
                                    "ORDER BY a.id", nativeQuery = true)
    List<Aposta> buscarPorNomePorEmail(String nome, String email);

    @Query(value = "SELECT * FROM aposta a, pessoa p " +
                    "WHERE a.id_pessoa = p.id " +
                        "AND a.nome = ?1 " +
                            "AND  p.email = ?2 " +
                                "AND a.id = ?3 ", nativeQuery = true)
    Aposta buscarPorNomePorEmailPorIdAposta(String nome, String email, int id_aposta);

    @Query(value = "SELECT * FROM aposta a, pessoa p " +
                    "WHERE a.id_pessoa = p.id " +
                        "AND  p.email = ?1 " +
                            "ORDER BY a.id", nativeQuery = true)
    List<Aposta> buscarPorEmail(String email);
}




