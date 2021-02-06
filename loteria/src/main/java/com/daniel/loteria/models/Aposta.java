package com.daniel.loteria.models;

import javax.persistence.*;

@Entity
@Table(
        name = "APOSTA",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "id_pessoa", "numeros"})}
)
public class Aposta {
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_pessoa",nullable=false)
    private Pessoa id_pessoa;

    @Column(name = "numeros", nullable = false)
    private Integer[] numeros;

    public Aposta() {
    }

    public Aposta( String nome, Pessoa id_pessoa, Integer[] numeros) {
        super();
        this.nome = nome;
        this.id_pessoa = id_pessoa;
        this.numeros = numeros;
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

    public Integer[] getNumeros() {
        return numeros;
    }

    public void setNumeros(Integer[] numeros) {
        this.numeros = numeros;
    }


}
