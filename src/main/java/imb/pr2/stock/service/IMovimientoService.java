package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Movimiento;

public interface IMovimientoService {
	public List<Movimiento> buscarMovimientos();
	public Movimiento buscarMovimientoPorId(Integer id);
	public void guardarMovimiento(Movimiento movimiento);
	public void eliminarMovimiento(Integer idMovimiento);
}
