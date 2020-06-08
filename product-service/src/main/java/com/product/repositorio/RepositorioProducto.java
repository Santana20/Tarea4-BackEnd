package com.product.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.entidades.Product;

public interface RepositorioProducto extends JpaRepository<Product, Long> {
	@Query("SELECT c FROM Product c WHERE c.codigo=:codigo")
	Product buscarProducto(@Param("codigo") Long codigo);
	
	@Query("select c from Product c where c.nombre like %?1%")
	List<Product> buscarNombre(String nombre);

	@Query("select c from Product c where c.precio between ?1 and ?2")
	List<Product> buscarporPrecio(double p1, double p2);
}
