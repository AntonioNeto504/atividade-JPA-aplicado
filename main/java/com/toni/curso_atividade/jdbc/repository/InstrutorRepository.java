package com.toni.curso_atividade.jdbc.repository;

import com.toni.curso_atividade.model.Instrutor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class InstrutorRepository {
    private final JdbcTemplate jdbcTemplate;

    public InstrutorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Instrutor save(Instrutor instrutor) {
        String sql = "INSERT INTO instrutor (nome, email) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, instrutor.getNome());
            ps.setString(2, instrutor.getEmail());
            return ps;
        }, keyHolder);
        instrutor.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return instrutor;
    }

    public Instrutor findById(Long id) {
        String sql = "SELECT * FROM instrutor WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToInstrutor, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Instrutor> findAll() {
        String sql = "SELECT * FROM instrutor";
        return jdbcTemplate.query(sql, this::mapRowToInstrutor);
    }

    public void update(Instrutor instrutor) {
        String sql = "UPDATE instrutor SET nome = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, instrutor.getNome(), instrutor.getEmail(), instrutor.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM instrutor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Instrutor mapRowToInstrutor(ResultSet rs, int rowNum) throws SQLException {
        return new Instrutor(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("email")
        );
    }
}