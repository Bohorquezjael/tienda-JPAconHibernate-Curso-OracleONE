package com.latam.alura.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DatosPersonales datosPersonales;

    public Cliente() {
    }
    
    public Cliente(String nombre, String dni) {
        this.datosPersonales = new DatosPersonales(nombre, dni);
    }
    
    public String getDni() {
        return this.datosPersonales.getDni();
    }

    public void setDni(String dni) {
        this.datosPersonales.setDni(dni);
    }

    // public Cliente(String nombre) { 
    //     this.datosPersonales = new DatosPersonales(nombre);
    // }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return this.datosPersonales.getNombre();
    }

    public void setNombre(String nombre) {
        this.datosPersonales.setNombre(nombre);;
    }
}
