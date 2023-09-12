package com.latam.alura.tienda.dao;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Categoria;

public class CategoriaDao {
    private EntityManager eM;
    
    public CategoriaDao(EntityManager eM){
        this.eM = eM;
    }

    public void guardar(Categoria categoria){
        this.eM.persist(categoria);
    }
    
}
