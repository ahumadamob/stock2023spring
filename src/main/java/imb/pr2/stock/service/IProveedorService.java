package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Proveedor;

public interface IProveedorService {
	public List<Proveedor> buscar();
	public Proveedor buscarPorId(Integer id);
	public Proveedor guardar(Proveedor proveedor);
	public void eliminar(Integer id);
	public Boolean exists(Integer id);
	

}
