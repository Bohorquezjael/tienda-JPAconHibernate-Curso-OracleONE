package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.latam.alura.tienda.modelo.Producto;

public class RegistroDeProducto {

    public static void main(String[] args) {
        Producto sombrero = new Producto();
        sombrero.setNombre("Sombrero Gucci ");
        sombrero.setDescripcion("sombrero de alta gama con detalles en piel");
        sombrero.setPrecio(new BigDecimal("18000.99"));

        EntityManagerFactory eMgrFactory = Persistence.createEntityManagerFactory("tienda");
        EntityManager eMgr = eMgrFactory.createEntityManager();
        eMgr.getTransaction().begin();

        eMgr.persist(sombrero);

        eMgr.getTransaction().commit();
        eMgr.close();
    }
}
