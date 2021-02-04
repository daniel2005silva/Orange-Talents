package com.daniel.loteria.models;

import javax.persistence.*;

@Entity
public class Aposta {
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "nome")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_pessoa",nullable=false)
    private Pessoa id_pessoa;

    @Column(name = "aposta")
    private Integer[] aposta;

    public Aposta() {
    }

    public Aposta( String nome, Pessoa id_pessoa, Integer[] aposta) {
        super();
        this.nome = nome;
        this.id_pessoa = id_pessoa;
        this.aposta = aposta;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
