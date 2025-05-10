package com.toni.curso_atividade.jpa.repository;


import com.toni.curso_atividade.jpa.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByTituloContainingIgnoreCase(String titulo);
    List<Curso> findByDuracaoHorasBetween(Double min, Double max);
    List<Curso> findByInstrutorId(Long instrutorId);

    @Query("SELECT c FROM Curso c WHERE c.instrutor.id = :instrutorId AND c.duracaoHoras >= :duracaoMin")
    List<Curso> findByInstrutorIdAndDuracaoHorasMin(Long instrutorId, Double duracaoMin);

    @Query("SELECT c FROM Curso c JOIN FETCH c.instrutor WHERE c.instrutor.id = :instrutorId")
    List<Curso> findByInstrutorIdWithFetch(Long instrutorId);
}