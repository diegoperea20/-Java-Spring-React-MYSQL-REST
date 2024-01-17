package com.exampleback.backend.servicio;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampleback.backend.model.Empleado;
import com.exampleback.backend.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
      return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarPorId(Integer idEmpleado) {
        Empleado empleado = empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
       return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}
