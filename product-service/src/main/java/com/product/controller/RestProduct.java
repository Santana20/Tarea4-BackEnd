package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.product.entidades.Product;
import com.product.servicio.ServicioProduct;

@RestController
@RequestMapping("/api")
public class RestProduct
{
	@Autowired
	private ServicioProduct servicioproducto;
	
	//REGISTRAR PRODUCTO
	@PostMapping("/producto/{codigo}")
	public Product registrarProducto (@PathVariable(value = "codigo") Long codigo, @RequestBody Product producto)
	{
		Product p;
		try
		{
			p = servicioproducto.registrarProducto(codigo, producto);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo registrar producto");
		}
		return p;
	}
	
	//MOSTRAR PRODUCTOS
	@GetMapping("/mostrarProductos")
	public List<Product> mostrarProductos(){
		List<Product> productos;
		try {
			productos=servicioproducto.mostrarProductos();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Productos");
		}
		return productos;
	}
	
	//ACTUALIZAR PRODUCTO
	@PostMapping("/actualizarProducto/{codigo}")
	public Product actualizarProducto(@PathVariable(value = "codigo") Long codigo,@RequestBody Product producto) {
		Product p;
		try {
			p=servicioproducto.actualizarProducto(codigo, producto);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede registrar producto");
		}
		return p;
	}
	
	
	//ELIMINAR PRODUCTO
	@DeleteMapping("/eliminarProducto/{codigo}")
	public Product eliminarProducto(@PathVariable(value = "codigo") Long codigo) {
		Product p;
		try {
			p=servicioproducto.eliminarProducto(codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede borrar");
		}
		return p;
	}
	
	//BUSCAR POR NOMBRE PRODUCTOS
	@GetMapping("/buscarNombreProducto/{nombre}")
	public List<Product> buscarNombreProducto(@PathVariable(value = "nombre") String nombre){
		List<Product> productos;
		try {
			productos=servicioproducto.buscarNombreProductos(nombre);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return productos;
	}
	
	//BUSCAR POR NOMBRE PRODUCTOS
	@GetMapping("/buscarporPrecioProducto/{p1}/{p2}")
	public List<Product> buscarporPrecioProducto(@PathVariable(value = "p1") double p1, @PathVariable(value="p2") double p2){
		List<Product> productos;
		try {
			productos=servicioproducto.buscarporPrecioProductos(p1, p2);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return productos;
	}
}
