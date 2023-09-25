package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Cliente;

public class ClienteDao {

    private EntityManager eM;

    public ClienteDao(EntityManager eM) {
        this.eM = eM;
    }

    public void guardar(Cliente cliente) {
        this.eM.persist(cliente);
    }

    public void actualizar(Cliente cliente) {
        this.eM.merge(cliente);
    }

    public void remover(Cliente cliente) {
        this.eM.merge(cliente);
        this.eM.remove(cliente);
    }

    public Cliente consultaPorId(Long id) {
        return eM.find(Cliente.class, id);
    }

    public List<Cliente> consultarTodos() {
        String jqpl = "SELECT P FROM Producto AS P";
        return eM.createQuery(jqpl, Cliente.class).getResultList();
    }

    public List<Cliente> consultaPorNombre(String nombre) {
        String jpql = " SELECT P FROM Producto AS P WHERE P.nombre=:nombre ";
        return eM.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Cliente> consultaPorNombreDeCategoria(String nombre) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return eM.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql = "SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return eM.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
