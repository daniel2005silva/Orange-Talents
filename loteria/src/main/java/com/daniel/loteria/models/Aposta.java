package com.daniel.loteria.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Aposta {
    @GeneratedValue
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa id_pessoa ;

    @Column(name = "aposta")
    private Integer[] aposta;

    public Aposta() {
    }

    public Aposta( Pessoa id_pessoa, Integer[] aposta) {
        super();
        this.id_pessoa = id_pessoa;
        this.aposta = aposta;
    }

    public int getId() {
        return id;
    }

    public Pessoa getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Pessoa id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Integer[] getAposta() {
        return aposta;
    }

    public void setAposta(Integer[] aposta) {
        this.aposta = aposta;
    }


}
