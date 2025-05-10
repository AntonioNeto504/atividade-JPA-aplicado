package com.toni.curso_atividade.jpa.repository;

import com.toni.curso_atividade.jpa.entity.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
}