package com.jpa.biblioteca.controller;

import com.jpa.biblioteca.service.EditorialService;
import com.jpa.biblioteca.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListadoController {

    private final LibroService libroService;
    private final EditorialService editorialService;

    public ListadoController(LibroService libroService, EditorialService editorialService) {
        this.libroService = libroService;
        this.editorialService = editorialService;
    }

    @GetMapping("/")
    public String mostrarPaginaPrincipal(Model model) {
        model.addAttribute("libros", libroService.buscarDestacados());
        return "listado";
    }

    @GetMapping("/buscar")
    public String buscarLibros(@RequestParam(name = "q", required = false) String consulta, Model model) {
        if (consulta != null && !consulta.trim().isEmpty()) {
            model.addAttribute("libros", libroService.buscar(consulta));
            model.addAttribute("consulta", consulta);
        } else {
            model.addAttribute("libros", libroService.buscarDestacados());
        }
        return "listado";
    }

    @GetMapping("/librosPorEditorial")
    public String mostrarLibrosPorEditorial(@RequestParam("editorialId") int editorialId, Model model) {
        model.addAttribute("libros", libroService.buscarPorEditorial(editorialId));
        
        // Obtener información de la editorial para mostrar en el título
        editorialService.buscarPorId(editorialId).ifPresent(editorial -> {
            model.addAttribute("editorialNombre", editorial.getNombre());
        });
        
        return "listado";
    }

    @GetMapping("/editoriales")
    public String mostrarEditoriales(Model model) {
        model.addAttribute("editoriales", editorialService.buscarTodos());
        return "editoriales";
    }
}