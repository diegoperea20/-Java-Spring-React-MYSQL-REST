package com.exampleback.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampleback.backend.model.Empleado;
import com.exampleback.backend.servicio.IEmpleadoServicio;

import java.util.*;

import org.apache.catalina.connector.Response;
import org.slf4j.*;


@RestController
/* http://localhost:8080/appempleados*/
@RequestMapping("appempleados")
@CrossOrigin(value = "http://localhost:3000")//the ip address of the server fronted
public class EmpleadoControlador {

    private static final Logger logger  = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping("/empleados") 
    public List<Empleado> obtenerEmpleados(){
        var empleados = empleadoServicio.listarEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
        
    }   

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado agregado: "+empleado.toString());
        return empleadoServicio.guardarEmpleado(empleado);
        
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarPorId(id);
        if (empleado == null){
             throw new RuntimeException("El empleado no existe");
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = empleadoServicio.buscarPorId(id);
        if (empleado == null){
            throw new RuntimeException("El empleado no existe");
        }
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.buscarPorId(id);
        if (empleado == null){
            throw new RuntimeException("El empleado no existe");
        }
        empleadoServicio.eliminarEmpleado(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
