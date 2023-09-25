package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.latam.alura.tienda.modelo.Producto;

public class ProductoDao {

    private EntityManager eM;
    
    public ProductoDao(EntityManager eM){
        this.eM = eM;
    }

    public void guardar(Producto producto){
        this.eM.persist(producto);
    }

    public void actualizar(Producto producto) {
        this.eM.merge(producto);
    }

    public void remover(Producto producto) {
        this.eM.merge(producto);
        this.eM.remove(producto);
    }

    public Producto consultaPorId(Long id) {
        return eM.find(Producto.class, id);
    }

    public List<Producto> consultarTodos(){
        String jqpl= "SELECT P FROM Producto AS P";
        return eM.createQuery(jqpl,Producto.class).getResultList();
    }
    public List<Producto> consultaPorNombre(String nombre){
        String jpql =" SELECT P FROM Producto AS P WHERE P.nombre=:nombre ";
        return eM.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorNombreDeCategoria(String nombre){
        String jpql="SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return eM.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql="SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return eM.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public BigDecimal consultarPrecio(String nombre) {
        return eM.createNamedQuery("Producto.consultarPrecio",BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public List<Producto> consultarPorParametrosConAPICriteria(String nombre, BigDecimal precio,LocalDate fecha){
		CriteriaBuilder builder = eM.getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> from = query.from(Producto.class);
		
		
		Predicate filtro = builder.and();
		if(nombre!=null && !nombre.trim().isEmpty()) {
			filtro=builder.and(filtro,builder.equal(from.get("nombre"), nombre));
		}
		if(precio!=null && !precio.equals(new BigDecimal(0))) {
			filtro=builder.and(filtro,builder.equal(from.get("precio"), precio));
		}
		if(fecha!=null) {
			filtro=builder.and(filtro,builder.equal(from.get("fechaDeRegistro"), fecha));
		}
		
		query=query.where(filtro);
		return eM.createQuery(query).getResultList();

	
	}
}
