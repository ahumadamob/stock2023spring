package imb3.stock.service;

import java.util.List;

import imb3.stock.entity.categorias;

public interface categoriaInterface {
	
	public List<categorias> getCategorias();
	public categorias getCategoriaById(Integer id);
	public void setCategoria(String nombre);
	public boolean deleteByIdCategoria(Integer id);
	public boolean modifyCategoria(Integer id,categorias categoria);
	
}
