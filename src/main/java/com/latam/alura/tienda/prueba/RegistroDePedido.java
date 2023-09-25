package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDePedido {

    public static void main(String[] args) {
        registrarProducto();
        Cliente cliente1 = new Cliente("jorge", "qwegtu3475");

    }

    private static void registrarProducto() {
        Categoria accesorios = new Categoria("ACCESORIOS");
        Producto sombrero = new Producto("Sombrero Gucci", "sombrero de alta gama con detalles en piel", new BigDecimal("18000.99"), accesorios );

        EntityManager eMgr = JPAUtils.getEntityManager();

        ProductoDao pDao = new ProductoDao(eMgr); 
        CategoriaDao cDao = new CategoriaDao(eMgr);

        eMgr.getTransaction().begin();

        cDao.guardar(accesorios);
        pDao.guardar(sombrero);

        eMgr.getTransaction().commit();
        eMgr.close();
    }
}
