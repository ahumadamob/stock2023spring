package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Categoria;

public interface ICategoriaInterface {
	
	public List<Categoria> getCategorias();
	public Categoria getCategoriaById(Integer id);
	public void setCategoria(String nombre);
	public boolean deleteByIdCategoria(Integer id);
	public boolean modifyCategoria(Integer id,Categoria categoria);
	
}
