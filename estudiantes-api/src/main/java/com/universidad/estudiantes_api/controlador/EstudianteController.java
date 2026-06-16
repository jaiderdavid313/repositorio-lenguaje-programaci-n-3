package com.universidad.estudiantes_api.controlador;

import com.universidad.estudiantes_api.modelo.Estudiante;
import com.universidad.estudiantes_api.servicio.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Aplica SRP: esta clase SOLO maneja peticiones HTTP
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    // Inyección por constructor ✔️
    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // GET /api/estudiantes → listar todos
    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // GET /api/estudiantes/buscar?nombre=ana
    // IMPORTANTE: Este endpoint debe ir ANTES de buscarPorId para evitar conflictos de ruta
    @GetMapping("/buscar")
    public ResponseEntity<List<Estudiante>> buscar(
            @RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    // GET /api/estudiantes/{id} → buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/estudiantes → crear nuevo
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estudiante e) {
        try {
            Estudiante creado = service.crear(e);
            return ResponseEntity.status(201).body(creado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // PUT /api/estudiantes/{id} → actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(
            @PathVariable Long id,
            @RequestBody Estudiante datos) {
        return service.actualizar(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/estudiantes/{id} → eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }
}