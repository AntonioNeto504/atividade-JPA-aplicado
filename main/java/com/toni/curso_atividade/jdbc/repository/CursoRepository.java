package com.toni.curso_atividade.jdbc.repository;


import com.toni.curso_atividade.model.Curso;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CursoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CursoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Curso save(Curso curso) {
        String sql = "INSERT INTO curso (titulo, duracao_horas, instrutor_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, curso.getTitulo());
            ps.setDouble(2, curso.getDuracaoHoras());
            ps.setLong(3, curso.getInstrutorId());
            return ps;
        }, keyHolder);
        curso.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return curso;
    }

    public Curso findById(Long id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToCurso, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Curso> findAll() {
        String sql = "SELECT * FROM curso";
        return jdbcTemplate.query(sql, this::mapRowToCurso);
    }

    public void update(Curso curso) {
        String sql = "UPDATE curso SET titulo = ?, duracao_horas = ?, instrutor_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId(), curso.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Curso> findByFilters(String titulo, Double duracaoMin, Double duracaoMax, Long instrutorId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM curso WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND UPPER(titulo) LIKE ?");
            params.add("%" + titulo.toUpperCase() + "%");
        }
        if (duracaoMin != null) {
            sql.append(" AND duracao_horas >= ?");
            params.add(duracaoMin);
        }
        if (duracaoMax != null) {
            sql.append(" AND duracao_horas <= ?");
            params.add(duracaoMax);
        }
        if (instrutorId != null) {
            sql.append(" AND instrutor_id = ?");
            params.add(instrutorId);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), this::mapRowToCurso);
    }

    public List<Curso> findByInstrutorId(Long instrutorId) {
        String sql = "SELECT c.* FROM curso c JOIN instrutor i ON c.instrutor_id = i.id WHERE i.id = ?";
        return jdbcTemplate.query(sql, this::mapRowToCurso, instrutorId);
    }

    public void saveBatch(List<Curso> cursos) {
        String sql = "INSERT INTO curso (titulo, duracao_horas, instrutor_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Curso curso = cursos.get(i);
                ps.setString(1, curso.getTitulo());
                ps.setDouble(2, curso.getDuracaoHoras());
                ps.setLong(3, curso.getInstrutorId());
            }

            @Override
            public int getBatchSize() {
                return cursos.size();
            }
        });
    }

    private Curso mapRowToCurso(ResultSet rs, int rowNum) throws SQLException {
        return new Curso(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getDouble("duracao_horas"),
                rs.getLong("instrutor_id")
        );
    }
}