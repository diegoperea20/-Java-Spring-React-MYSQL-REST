package com.exampleback.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleback.backend.model.Empleado;


public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
    
}
