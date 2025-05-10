package com.toni.curso_atividade.jdbc.service;

import com.toni.curso_atividade.jdbc.repository.CursoRepository;
import com.toni.curso_atividade.model.Curso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("jdbcCursoService")
public class CursoService {
    private final CursoRepository cursoRepository;
    private final JdbcTemplate jdbcTemplate;

    public CursoService(CursoRepository cursoRepository, JdbcTemplate jdbcTemplate) {
        this.cursoRepository = cursoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(rollbackOn = {Exception.class}) // Corrected syntax
    public Curso criarCurso(Curso curso) throws Exception {
        if (curso.getDuracaoHoras() < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa");
        }

        Curso cursoSalvo = cursoRepository.save(curso);

        String sql = "INSERT INTO log_curso (curso_id, titulo, data_criacao) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, cursoSalvo.getId(), cursoSalvo.getTitulo(), LocalDateTime.now());

        return cursoSalvo;
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public void update(Curso curso) {
        cursoRepository.update(curso);
    }

    public void delete(Long id) {
        cursoRepository.delete(id);
    }

    public List<Curso> findByFilters(String titulo, Double duracaoMin, Double duracaoMax, Long instrutorId) {
        return cursoRepository.findByFilters(titulo, duracaoMin, duracaoMax, instrutorId);
    }

    public List<Curso> findByInstrutorId(Long instrutorId) {
        return cursoRepository.findByInstrutorId(instrutorId);
    }

    public void saveBatch(List<Curso> cursos) {
        cursoRepository.saveBatch(cursos);
    }
}