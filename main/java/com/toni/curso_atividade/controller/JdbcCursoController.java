package com.toni.curso_atividade.controller;

import com.toni.curso_atividade.jdbc.service.CursoService;
import com.toni.curso_atividade.model.Curso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc/cursos")
public class JdbcCursoController {
    private final CursoService cursoService;

    public JdbcCursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> criar(@Valid @RequestBody Curso curso) throws Exception {
        return ResponseEntity.ok(cursoService.criarCurso(curso));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        curso.setId(id); // Linha 38
        cursoService.update(curso);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Curso>> filtrar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double duracaoMin,
            @RequestParam(required = false) Double duracaoMax,
            @RequestParam(required = false) Long instrutorId) {
        return ResponseEntity.ok(cursoService.findByFilters(titulo, duracaoMin, duracaoMax, instrutorId));
    }
}