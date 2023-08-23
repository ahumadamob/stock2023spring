package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Proveedor;

public interface IProveedorService {
	public List<Proveedor> buscarProveedores();
	public Proveedor buscarProveedorPorId(Integer id);
	public void guardarProveedor(Proveedor proveedor);
	public void eliminarProveedor(Integer id);
	

}
