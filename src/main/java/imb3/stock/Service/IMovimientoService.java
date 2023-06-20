package imb3.stock.Service;

import java.util.List;

import imb3.stock.entity.MovimientoStock;

public interface IMovimientoService {
	public List<MovimientoStock> buscarMovimientos();
	public MovimientoStock buscarMovimientoPorId(Integer id);
	public void guardarMovimiento(MovimientoStock movimiento);
	public void eliminarMovimiento(Integer idMovimiento);
}
