package com.universidad.estudiantes_api.repositorio;

import com.universidad.estudiantes_api.modelo.Estudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// Aplica SRP: esta clase SOLO maneja almacenamiento
// Aplica DIP: será inyectada como abstracción
@Repository
public class EstudianteRepository {

    // Base de datos en memoria
    private final List<Estudiante> baseDatos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    // Constructor: carga datos de ejemplo
    public EstudianteRepository() {
        baseDatos.add(new Estudiante(contadorId.getAndIncrement(),
                "Ana García", "ana@uni.edu", "Ingeniería de Sistemas", 4.5));
        baseDatos.add(new Estudiante(contadorId.getAndIncrement(),
                "Carlos López", "carlos@uni.edu", "Matemáticas", 3.8));
        baseDatos.add(new Estudiante(contadorId.getAndIncrement(),
                "María Torres", "maria@uni.edu", "Ingeniería de Sistemas", 4.2));
    }

    public List<Estudiante> findAll() {
        return new ArrayList<>(baseDatos);
    }

    public Optional<Estudiante> findById(Long id) {
        return baseDatos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public Estudiante save(Estudiante e) {
        if (e.getId() == null) {
            // Nuevo estudiante: asignar ID automático
            e.setId(contadorId.getAndIncrement());
            baseDatos.add(e);
        } else {
            // Actualizar existente
            for (int i = 0; i < baseDatos.size(); i++) {
                if (baseDatos.get(i).getId().equals(e.getId())) {
                    baseDatos.set(i, e);
                    return e;
                }
            }
        }
        return e;
    }

    public boolean deleteById(Long id) {
        return baseDatos.removeIf(e -> e.getId().equals(id));
    }

    // Reto Final: Búsqueda por nombre
    public List<Estudiante> findByNombre(String texto) {
        return baseDatos.stream()
                .filter(e -> e.getNombre()
                        .toLowerCase()
                        .contains(texto.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }
}