package imb3.stock.service;

import java.util.List;

import imb3.stock.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> buscarEmpleados();
	public Empleado buscarEmpleadoPorId(Integer id);
	public void guardarEmpleado(Empleado empleado);
	public void eliminarEmpleado(Integer id);
	
}
	

