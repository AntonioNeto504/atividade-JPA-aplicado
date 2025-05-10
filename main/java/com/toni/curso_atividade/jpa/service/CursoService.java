package com.toni.curso_atividade.jpa.service;

import com.toni.curso_atividade.jpa.entity.Curso;
import com.toni.curso_atividade.jpa.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jpaCursoService")
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso criarCurso(Curso curso) throws Exception {
        if (curso.getDuracaoHoras() < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa");
        }
        return cursoRepository.save(curso);
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public void update(Curso curso) {
        cursoRepository.save(curso);
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> findByFilters(String titulo, Double duracaoMin, Double duracaoMax, Long instrutorId) {
        return cursoRepository.findByFilters(titulo, duracaoMin, duracaoMax, instrutorId);
    }

    public List<Curso> findByInstrutorIdWithFetch(Long instrutorId) {
        return cursoRepository.findByInstrutorIdWithFetch(instrutorId);
    }
}