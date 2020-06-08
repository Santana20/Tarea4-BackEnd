package com.product.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.entidades.Pizzeria;

public interface RepositorioPizzeria extends JpaRepository<Pizzeria, Long> {
	@Query("SELECT c FROM Pizzeria c WHERE c.codigo=:codigo")
	Pizzeria obtenerPizzeria(@Param(value = "codigo") Long codigo);
}
