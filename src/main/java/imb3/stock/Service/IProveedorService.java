package imb3.stock.Service;

import java.util.List;

import imb3.stock.entity.Proveedor;

public interface IProveedorService {
	public List<Proveedor> buscarProveedores();
	public Proveedor buscarProveedorPorId(Integer id);
	public void guardarProveedor(Proveedor proveedor);
	public void eliminarProveedor(Integer id);
	

}
