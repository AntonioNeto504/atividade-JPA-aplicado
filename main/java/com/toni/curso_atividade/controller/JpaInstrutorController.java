package com.toni.curso_atividade.controller;

import com.toni.curso_atividade.jpa.entity.Instrutor;
import com.toni.curso_atividade.jpa.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/instrutores")
public class JpaInstrutorController {
    private final InstrutorService instrutorService;

    public JpaInstrutorController(InstrutorService instrutorService) {
        this.instrutorService = instrutorService;
    }

    @PostMapping
    public ResponseEntity<Instrutor> criar(@Valid @RequestBody Instrutor instrutor) {
        return ResponseEntity.ok(instrutorService.criarInstrutor(instrutor));
    }

    @GetMapping
    public ResponseEntity<List<Instrutor>> listar() {
        return ResponseEntity.ok(instrutorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> buscarPorId(@PathVariable Long id) {
        Instrutor instrutor = instrutorService.buscarPorId(id);
        return instrutor != null ? ResponseEntity.ok(instrutor) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody Instrutor instrutor) {
        instrutor.setId(id);
        instrutorService.atualizarInstrutor(instrutor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        instrutorService.deletarInstrutor(id);
        return ResponseEntity.ok().build();
    }
}