package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.VO.RelatorioDeVenta;
import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDePedido {

    public static void main(String[] args) {
        registrarProducto();
        EntityManager eMgr = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(eMgr);
        Producto producto = productoDao.consultaPorId(1L);
        ClienteDao clienteDao = new ClienteDao(eMgr);
        PedidoDao pedidoDao = new PedidoDao(eMgr);
        Cliente cliente1 = new Cliente("jorge", "qwegtu3475");
        Pedido pedido = new Pedido(cliente1);
        pedido.agregarItems(new ItemsPedido(4, producto, pedido));
        eMgr.getTransaction().begin();
        clienteDao.guardar(cliente1);
        pedidoDao.guardar(pedido);
        eMgr.getTransaction().commit();
        BigDecimal total = pedidoDao.valorTotalVendido();
        System.out.println("el valor de la venta es: " + total);
        List<RelatorioDeVenta> Ventas = pedidoDao.relatorioDeVentasVO();
        Ventas.forEach(System.out::println);
        eMgr.close();

    }

    private static void registrarProducto() {
        Categoria accesorios = new Categoria("ACCESORIOS");
        Producto sombrero = new Producto("Sombrero Gucci", "sombrero de alta gama con detalles en piel",
                new BigDecimal("18000.99"), accesorios);

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
