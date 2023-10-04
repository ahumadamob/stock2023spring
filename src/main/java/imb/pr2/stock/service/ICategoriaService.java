package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> getCategorias();
	public Categoria getCategoriaById(Integer id);
	public void setCategoria(Categoria categoria);
	public void deleteByIdCategoria(Integer id);
	public boolean exists(Integer id);

}
