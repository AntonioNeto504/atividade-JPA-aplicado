package com.toni.curso_atividade.controller;

import com.toni.curso_atividade.jdbc.service.InstrutorService;
import com.toni.curso_atividade.model.Instrutor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc/instrutores")
public class JdbcInstrutorController {
    private final InstrutorService instrutorService;

    public JdbcInstrutorController(InstrutorService instrutorService) {
        this.instrutorService = instrutorService;
    }

    @PostMapping
    public ResponseEntity<Instrutor> criar(@Valid @RequestBody Instrutor instrutor) {
        return ResponseEntity.ok(instrutorService.save(instrutor));
    }

    @GetMapping
    public ResponseEntity<List<Instrutor>> listar() {
        return ResponseEntity.ok(instrutorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> buscarPorId(@PathVariable Long id) {
        Instrutor instrutor = instrutorService.findById(id);
        return instrutor != null ? ResponseEntity.ok(instrutor) : ResponseEntity.notFound().build();
    }
}