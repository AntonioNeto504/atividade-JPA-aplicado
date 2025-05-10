package com.toni.curso_atividade.jdbc.service;

import com.toni.curso_atividade.jdbc.repository.InstrutorRepository;
import com.toni.curso_atividade.model.Instrutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrutorService {
    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    public Instrutor save(Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }

    public Instrutor findById(Long id) {
        return instrutorRepository.findById(id);
    }

    public List<Instrutor> findAll() {
        return instrutorRepository.findAll();
    }

    public void update(Instrutor instrutor) {
        instrutorRepository.update(instrutor);
    }

    public void delete(Long id) {
        instrutorRepository.delete(id);
    }
}