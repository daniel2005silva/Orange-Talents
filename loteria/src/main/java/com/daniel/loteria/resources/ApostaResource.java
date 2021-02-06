package com.daniel.loteria.resources;

import com.daniel.loteria.models.Aposta;
import com.daniel.loteria.repositories.ApostaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/apostas")
public class ApostaResource {
    private ApostaRepository apostaRepository;

    public ApostaResource(ApostaRepository apostaRepository) {
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
                    aposta.setNome(newAposta.getNome());
                    aposta.setId_pessoa(newAposta.getId_pessoa());
                    aposta.setNumeros(newAposta.getNumeros());
                    Aposta apostaUpdate = apostaRepository.save(aposta);
                    return  ResponseEntity.ok().body(apostaUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/'{nome}'")
    public List<Aposta> verNome(@PathVariable(value = "nome") String nome){
        try{
            return apostaRepository.findByNome(nome);
        }catch (NoSuchElementException nsee){
            return  null;
        }
    }

    @GetMapping(path = "/todasDeUmJogo")
    public List<Aposta> verTodasApostasNumJogo(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "email") String email
    ){
        try{
            return apostaRepository.buscarPorNomePorEmail(nome, email);
        }catch (NoSuchElementException nsee){
            return  null;
        }
    }

    @GetMapping(path = "/numeros")
    public Integer[] verNumerosDaAposta(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "id_aposta") int id
    ){
        try{
            Aposta aposta = new Aposta();
            aposta = apostaRepository.buscarPorNomePorEmailPorIdAposta(nome, email, id);
            return aposta.getNumeros();
        }catch (NoSuchElementException nsee){
            return  null;
        }
    }

    @GetMapping(path = "/todas")
    public List<Aposta> verTodasApostas(
            @RequestParam(value = "email") String email
    ){
        try{
            return apostaRepository.buscarPorEmail(email);
        }catch (NoSuchElementException nsee){
            return  null;
        }
    }
}
