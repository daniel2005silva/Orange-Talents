package com.daniel.loteria.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pessoa {
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
