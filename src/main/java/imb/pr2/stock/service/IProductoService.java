package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Producto;

public interface IProductoService {
	public List<Producto> buscarProductos();
	public Producto buscarProductoPorId (Integer id);
	public void guardarProducto(Producto producto);
	public void eliminarProducto(Integer id);
	public boolean existe(Integer id);
}
