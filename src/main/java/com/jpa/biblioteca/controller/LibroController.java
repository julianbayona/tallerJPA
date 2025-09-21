package com.jpa.biblioteca.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.biblioteca.domain.Libro;
import com.jpa.biblioteca.service.LibroService;

@RestController
public class LibroController {

    private LibroService libroService;

    @RequestMapping("/librosPorEditorial")
    public String listarLibrosPorEditorial(int editorialId, Model model){
        List<Libro> libros = libroService.buscarPorEditorial(editorialId);
        model.addAttribute("libros", libros);
        return "listado";
    }

    @RequestMapping("/buscar")
    public String buscar(String consulta, Model model){
        List<Libro> libros = libroService.buscar(consulta);
        model.addAttribute("libros", libros);
        return "listado";
    }
    
}
