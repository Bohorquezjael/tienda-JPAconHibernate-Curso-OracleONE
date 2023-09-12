package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

    public static void main(String[] args) {
        Producto sombrero = new Producto();
        sombrero.setNombre("Sombrero Gucci ");
        sombrero.setDescripcion("sombrero de alta gama con detalles en piel");
        sombrero.setPrecio(new BigDecimal("18000.99"));

        EntityManager eMgr = JPAUtils.getEntityManager();

        ProductoDao pDao = new ProductoDao(eMgr); 

        eMgr.getTransaction().begin();

        pDao.guardar(sombrero);

        eMgr.getTransaction().commit();
        eMgr.close();
    }
}
