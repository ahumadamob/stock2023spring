package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> buscar();
	public Empleado buscarPorId(Integer id);
	public Empleado guardar(Empleado empleado);
	public void eliminar(Integer id);
	public boolean existe (Integer id);
	
}
	

