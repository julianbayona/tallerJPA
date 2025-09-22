package com.jpa.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa.biblioteca.domain.Libro;
import com.jpa.biblioteca.service.LibroService;

/**
 * Controlador REST para la gestión de libros con configuración CORS específica
 */
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(
    origins = {
        "http://localhost:3000",    // React
        "http://localhost:4200",    // Angular  
        "http://localhost:5173",    // Vite
        "http://localhost:5200",
        "http://localhost:5200", 
        "http://127.0.0.1:5200",    // Testing local
        "https://midominio.com"     // Producción

    },
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
    allowedHeaders = "*",
    allowCredentials = "true"
)
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /**
     * Obtener todos los libros destacados
     */
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.buscarDestacados();
        return ResponseEntity.ok(libros);
    }

    /**
     * Buscar libros por editorial
     */
    @GetMapping("/editorial/{editorialId}")
    public ResponseEntity<List<Libro>> listarLibrosPorEditorial(@PathVariable int editorialId) {
        List<Libro> libros = libroService.buscarPorEditorial(editorialId);
        return ResponseEntity.ok(libros);
    }

    /**
     * Buscar libros por consulta de texto
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscar(@RequestParam String consulta) {
        List<Libro> libros = libroService.buscar(consulta);
        return ResponseEntity.ok(libros);
    }

    /**
     * Crear un nuevo libro
     */
    @PostMapping
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {
        libroService.guardar(libro);
        return ResponseEntity.ok("Libro creado exitosamente");
    }

    /**
     * Endpoint de prueba para verificar CORS
     */
    @GetMapping("/test-cors")
    public ResponseEntity<String> testCors() {
        return ResponseEntity.ok("CORS está funcionando correctamente!");
    }
}
