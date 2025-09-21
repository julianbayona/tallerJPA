package com.jpa.biblioteca.controller;

import com.jpa.biblioteca.domain.Libro;
import com.jpa.biblioteca.service.EditorialService;
import com.jpa.biblioteca.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibroCrudController {

    private final EditorialService editorialService;
    private final LibroService libroService;

    public LibroCrudController(LibroService libroService, EditorialService editorialService) {
        this.libroService = libroService;
        this.editorialService = editorialService;
    }

    @RequestMapping("/libros/crear")
    public String mostrarFormAlta(Model model) {
        model.addAttribute("editoriales", editorialService.buscarTodos());
        model.addAttribute("libro", new Libro());
        return "formLibro";
    }

    @PostMapping("/libros/guardar")
    public String guardar(Libro libro) {
        libroService.guardar(libro);
        return "redirect:/";
    }
    
}
