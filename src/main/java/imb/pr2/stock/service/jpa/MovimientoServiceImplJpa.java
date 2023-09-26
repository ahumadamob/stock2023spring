package imb.pr2.stock.service.jpa;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Movimiento;
import imb.pr2.stock.repository.MovimientoRepository;
import imb.pr2.stock.service.IMovimientoService;

@Service
@Primary
public class MovimientoServiceImplJpa implements IMovimientoService {

	@Autowired
	MovimientoRepository repo;
	
	@Override
	public List<Movimiento> buscarMovimientos() {
		return repo.findAll();
	}

	@Override
	public Movimiento buscarMovimientoPorId(Integer id) {
		return repo.findById(id).orElse(null);	
	}

	@Override
	public void guardarMovimiento(Movimiento movimiento) {
		repo.save(movimiento);
	}

	@Override
	public void eliminarMovimiento(Integer idMovimiento) {
		repo.deleteById(idMovimiento);
	}

	@Override 
	public boolean existe(Integer id) {
		return repo.existsById(id); 
	}
}
