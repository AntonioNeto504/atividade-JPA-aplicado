package com.toni.curso_atividade.jpa.service;

import com.toni.curso_atividade.jpa.entity.Instrutor;
import com.toni.curso_atividade.jpa.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrutorService {
    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    public Instrutor criarInstrutor(Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }

    public Instrutor buscarPorId(Long id) {
        return instrutorRepository.findById(id).orElse(null);
    }

    public List<Instrutor> listarTodos() {
        return instrutorRepository.findAll();
    }

    public void atualizarInstrutor(Instrutor instrutor) {
        instrutorRepository.save(instrutor);
    }

    public void deletarInstrutor(Long id) {
        instrutorRepository.deleteById(id);
    }
}