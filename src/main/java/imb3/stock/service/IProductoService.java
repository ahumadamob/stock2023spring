package imb3.stock.service;

import java.util.List;

import imb3.stock.entity.Producto;

public interface IProductoService {
	public List<Producto> buscarProductos();
	public Producto buscarProductoPorId (Integer id);
	public void guardarProducto(Producto producto);
	public void eliminarProducto(Integer id);
}
