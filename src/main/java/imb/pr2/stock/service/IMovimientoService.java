package imb.pr2.stock.service;

import java.util.List;

import imb.pr2.stock.entity.Movimiento;

public interface IMovimientoService {
	public List<Movimiento> buscar();
	public Movimiento buscarPorId(Integer id);
	public Movimiento guardar(Movimiento movimiento);
	public void eliminar(Integer idMovimiento);
	public boolean existe(Integer id);
}
