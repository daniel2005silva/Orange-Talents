package com.daniel.loteria.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name="PESSOA",
        uniqueConstraints={@UniqueConstraint(columnNames="email")}
)
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

    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
