package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;

public class PedidoDao {

    private EntityManager eM;

    public PedidoDao(EntityManager eM) {
        this.eM = eM;
    }

    public void guardar(Pedido pedido) {
        this.eM.persist(pedido);
    }

    public void actualizar(Pedido pedido) {
        this.eM.merge(pedido);
    }

    public void remover(Pedido pedido) {
        this.eM.merge(pedido);
        this.eM.remove(pedido);
    }

    public Pedido consultaPorId(Long id) {
        return eM.find(Pedido.class, id);
    }

    public List<Pedido> consultarTodos() {
        String jqpl = "SELECT P FROM Producto AS P";
        return eM.createQuery(jqpl, Pedido.class).getResultList();
    }

    public List<Pedido> consultaPorNombre(String nombre) {
        String jpql = " SELECT P FROM Producto AS P WHERE P.nombre=:nombre ";
        return eM.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Pedido> consultaPorNombreDeCategoria(String nombre) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return eM.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql = "SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return eM.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
