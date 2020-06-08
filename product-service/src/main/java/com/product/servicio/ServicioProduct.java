package com.product.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.entidades.Pizzeria;
import com.product.entidades.Product;
import com.product.repositorio.RepositorioPizzeria;
import com.product.repositorio.RepositorioProducto;

@Service
public class ServicioProduct {
	@Autowired
	private RepositorioProducto repoProducto;
	
	@Autowired
	private RepositorioPizzeria repoPizzeria;
	
	//REGISTRAR PRODUCTO.
	@Transactional(rollbackFor = Exception.class)
	public Product registrarProducto(Long codigo, Product producto) throws Exception 
	{
		Pizzeria pizzeria=repoPizzeria.obtenerPizzeria(codigo);
		producto.setPizzeria(pizzeria);
		return repoProducto.save(producto);
	}
	
	//OBTENER PRODUCTO
	public Product obtenerProducto(Long codigo) throws Exception
	{
		Product p;
		p = repoProducto.buscarProducto(codigo);
		if ( p == null ) throw new Exception("Producto no encontrado");
		return p;
	}
	
	//ACTUALIZAR PRODUCTO
	public Product actualizarProducto(Long codigo,Product producto) throws Exception 
	{
		Product p=obtenerProducto(codigo);
		if(producto.getNombre()!=null) {
			p.setNombre(producto.getNombre());
		}
		if(producto.getDescripcion()!=null) {
			p.setDescripcion(producto.getDescripcion());  
		}
		if(producto.getPrecio()!=0) {
			p.setPrecio(producto.getPrecio());
		}
		
		return repoProducto.save(p);
	}
	
	//ELIMINAR UN PRODUCTO
	public Product eliminarProducto(Long codigo) throws Exception
	{
		Product p;
		p=repoProducto.buscarProducto(codigo);
		if(p.getCodigo()!=null) {
			repoProducto.delete(p);
		}else {
			throw new Exception();
		}
		return p;
	}
	
	//MOSTRAR LISTA PRODUCTOS
	public List<Product> mostrarProductos()
	{
		return repoProducto.findAll();
	}
	
	//BUSCAR POR NOMBRE EN PRODUCTOS
	public List<Product> buscarNombreProductos(String nombre) throws Exception
	{
		List<Product> productos;
		productos=repoProducto.buscarNombre(nombre);
		if(productos==null)throw new Exception("No se encontro productos en el rango pedido.");
		return productos;
	}
	
	//BUSCAR POR PRECIO EN PRODUCTOS
	public List<Product> buscarporPrecioProductos(double p1, double p2) throws Exception
	{
		List<Product> productos;
		productos=repoProducto.buscarporPrecio(p1, p2);
		if(productos==null)throw new Exception("No se encontro productos en el rango pedido.");
		return productos;
	}
}
