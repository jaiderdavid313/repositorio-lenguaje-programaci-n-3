package com.universidad.estudiantes_api.servicio;

import com.universidad.estudiantes_api.modelo.Estudiante;
import com.universidad.estudiantes_api.repositorio.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Aplica SRP: esta clase SOLO contiene lógica de negocio
@Service
public class EstudianteService {

    // Aplica DIP: depende de la clase concreta (podría ser interfaz)
    private final EstudianteRepository repo;

    // Aplica DI por constructor ✔️
    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public List<Estudiante> obtenerTodos() {
        return repo.findAll();
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Estudiante crear(Estudiante e) {
        // Validaciones de negocio
        if (e.getNombre() == null || e.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");
        if (e.getEmail() == null || !e.getEmail().contains("@"))
            throw new IllegalArgumentException("Email inválido");
        if (e.getPromedio() < 0.0 || e.getPromedio() > 5.0)
            throw new IllegalArgumentException("Promedio debe estar entre 0 y 5");

        e.setId(null); // Asegura que se genere un nuevo ID
        return repo.save(e);
    }

    public Optional<Estudiante> actualizar(Long id, Estudiante datos) {
        return repo.findById(id).map(existente -> {
            existente.setNombre(datos.getNombre());
            existente.setEmail(datos.getEmail());
            existente.setCarrera(datos.getCarrera());
            existente.setPromedio(datos.getPromedio());
            return repo.save(existente);
        });
    }

    public boolean eliminar(Long id) {
        return repo.deleteById(id);
    }

    // Reto Final: Búsqueda por nombre
    public List<Estudiante> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
}