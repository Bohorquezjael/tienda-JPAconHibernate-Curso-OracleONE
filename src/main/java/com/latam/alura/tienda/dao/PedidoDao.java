package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.VO.RelatorioDeVenta;
import com.latam.alura.tienda.modelo.Pedido;

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

    public BigDecimal valorTotalVendido(){
                            //avg max min 
        String jpql = "SELECT SUM (p.valorTotal) FROM Pedido p";
        return eM.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVentas(){
        String jpql = "SELECT producto.nombre, SUM(item.cantidad), MAX(pedido.fecha) FROM Pedido pedido JOIN pedido.items item JOIN item.producto producto GROUP BY producto.nombre ORDER BY item.cantidad DESC ";
        return eM.createQuery(jpql, Object[].class).getResultList();
    }

    public List<RelatorioDeVenta> relatorioDeVentasVO(){
        String jpql = "SELECT new com.latam.alura.tienda.VO.RelatorioDeVenta(producto.nombre, SUM(item.cantidad), MAX(pedido.fecha)) FROM Pedido pedido JOIN pedido.items item JOIN item.producto producto GROUP BY producto.nombre ORDER BY item.cantidad DESC ";
        return eM.createQuery(jpql, RelatorioDeVenta.class).getResultList();
    }
}
