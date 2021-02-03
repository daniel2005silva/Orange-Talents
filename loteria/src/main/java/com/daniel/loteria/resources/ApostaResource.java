package com.daniel.loteria.resources;

import com.daniel.loteria.models.Aposta;
import com.daniel.loteria.repositories.ApostaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apostas")
public class ApostaResource {
    private ApostaRepository apostaRepository;

    public ApostaResource(ApostaRepository apostaRepository){
        super();
        this.apostaRepository = apostaRepository;
    }

    @PostMapping
    public ResponseEntity<Aposta> save(@RequestBody Aposta aposta){
        apostaRepository.save(aposta);
        return  new ResponseEntity<>(aposta, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Aposta>> getAll(){
        List<Aposta> apostas = new ArrayList<>();
        apostas = apostaRepository.findAll();
        return  new ResponseEntity<>(apostas, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Aposta>> getById(@PathVariable Integer id){
        Optional<Aposta> aposta;
        try{
            aposta = apostaRepository.findById(id);
            return new ResponseEntity<Optional<Aposta>>(aposta, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return  new ResponseEntity<Optional<Aposta>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Aposta>> deleteById(@PathVariable Integer id){
        try{
            apostaRepository.deleteById(id);
            return  new ResponseEntity<Optional<Aposta>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Aposta>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Aposta> update(@PathVariable Integer id, @RequestBody Aposta newAposta){
        return apostaRepository.findById(id)
                .map(aposta -> {
                    aposta.setId_pessoa(newAposta.getId_pessoa());
                    aposta.setAposta(newAposta.getAposta());
                    Aposta apostaUpdate = apostaRepository.save(aposta);
                    return  ResponseEntity.ok().body(apostaUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
    



    @PersistenceContext
    private EntityManager manager;

    @GetMapping(path = "/{id}/{email}")
    public ResponseEntity findBy(@PathVariable Integer id, String email) {
        try{
            TypedQuery<Aposta> query = manager
                    .createQuery(
                            "select a.aposta from Aposta a, Pessoa p where a.id = :id and a.id_pessoa = p.id and p.email = :email",
                            Aposta.class);
            query.setParameter("id", id);
            query.setParameter("email", email);
            return new ResponseEntity(query, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return  new ResponseEntity<Optional<Aposta>>(HttpStatus.NOT_FOUND);
        }
    }
}
