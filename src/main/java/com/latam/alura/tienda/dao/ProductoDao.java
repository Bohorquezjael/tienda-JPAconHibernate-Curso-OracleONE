package com.latam.alura.tienda.dao;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Producto;

public class ProductoDao {

    private EntityManager eM;
    
    public ProductoDao(EntityManager eM){
        this.eM = eM;
    }

    public void guardar(Producto producto){
        this.eM.persist(producto);
    }
}
