package com.exampleback.backend.servicio;

import java.util.*;

import com.exampleback.backend.model.Empleado;

public interface IEmpleadoServicio {
    public List<Empleado> listarEmpleados();

    public Empleado buscarPorId(Integer idEmpleado);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}
