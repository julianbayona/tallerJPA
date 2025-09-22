package com.jpa.biblioteca.service;

import com.jpa.biblioteca.domain.Editorial;
import com.jpa.biblioteca.repository.EditorialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {

    private final EditorialRepository editorialRepository;

   
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    public List<Editorial> buscarTodos() {
        return editorialRepository.findAll();
    }

    public Optional<Editorial> buscarPorId(int id) {
        return editorialRepository.findById(id);
    }
}
