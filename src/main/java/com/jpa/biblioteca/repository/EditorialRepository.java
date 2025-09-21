package com.jpa.biblioteca.repository;

import com.jpa.biblioteca.domain.Editorial;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Collection;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Integer> {

    // Métodos de búsqueda ordenada
    List<Editorial> findAllByOrderByNombreAsc();
    List<Editorial> findAllByOrderByNombreDesc();

    // Búsqueda por nombre
    Optional<Editorial> findByNombre(String nombre);
    List<Editorial> findByNombreContainingIgnoreCase(String fragmento);
    
    // Búsqueda por fragmento al inicio del nombre
    List<Editorial> findByNombreStartingWithIgnoreCase(String prefijo);
    
    // Verificaciones de existencia
    boolean existsByNombre(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
    
    // Búsqueda por múltiples IDs
    List<Editorial> findByIdIn(Collection<Integer> ids);
    
    // Contar editoriales por fragmento
    long countByNombreContainingIgnoreCase(String fragmento);
    
    // Buscar las primeras N editoriales
    List<Editorial> findTop10ByOrderByNombreAsc();
    
    // Eliminar por nombre
    void deleteByNombre(String nombre);
    

}