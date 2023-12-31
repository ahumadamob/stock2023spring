package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> buscarTodos();
	
	public List<Categoria> buscarHabilitados(boolean habilitado);
	
	public Categoria buscarPorId(Integer id);
	
	public Categoria guardar(Categoria categoria);
	
	public void eliminar(Integer id);

	public boolean existe(Integer id);

}
