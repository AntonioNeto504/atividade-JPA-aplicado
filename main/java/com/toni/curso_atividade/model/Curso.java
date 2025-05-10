package com.toni.curso_atividade.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Curso {
    private Long id;
    @NotNull(message = "Título não pode ser nulo")
    private String titulo;
    @NotNull(message = "Duração não pode ser nula")
    @Positive(message = "Duração deve ser positiva")
    private Double duracaoHoras;
    private Long instrutorId;

    public Curso() {}

    public Curso(Long id, String titulo, Double duracaoHoras, Long instrutorId) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoHoras = duracaoHoras;
        this.instrutorId = instrutorId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getDuracaoHoras() {
        return duracaoHoras;
    }

    public Long getInstrutorId() {
        return instrutorId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDuracaoHoras(Double duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public void setInstrutorId(Long instrutorId) {
        this.instrutorId = instrutorId;
    }
}