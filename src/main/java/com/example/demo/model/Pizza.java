package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Il nome della pizza non può essere nullo")
    @NotBlank(message = "Il nome della pizza non può essere vuoto")
    @Size(min = 3, max = 30, message = "Il nome della pizza deve essere compreso tra 3 e 30 caratteri")
    private String nome;

    @NotNull(message = "La descrizione della pizza non può essere nulla")
    @NotBlank(message = "La descrizione della pizza non può essere vuota")
    @Size(min = 10, max = 300, message = "La descrizione della pizza deve essere compresa tra 10 e 300 caratteri")
    private String descrizione;

    @NotNull(message = "URL della foto non può essere nullo")
    @NotBlank(message = "URL della foto non può essere vuoto")
    private String foto;

    @NotNull(message = "Il prezzo della pizza non può essere nullo")
    @Min(value = 0, message = "Il prezzo della pizza deve essere maggiore o uguale a zero")
    private Double prezzo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    
}
