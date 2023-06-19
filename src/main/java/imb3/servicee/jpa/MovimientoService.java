package imb3.servicee.jpa;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.Service.IMovimientoService;
import imb3.entity.MovimientoStock;
import imb3.repository.MovimientoRepository;

@Primary
@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	private MovimientoRepository repo;
	@Override
	public List<MovimientoStock> buscarMovimientos() {
		return repo.findAll();
	}

	@Override
	public MovimientoStock buscarMovimientoPorId(Integer id) {
		Optional<MovimientoStock> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

	@Override
	public void guardarMovimiento(MovimientoStock movimiento) {
		repo.save(movimiento);
	}

	@Override
	public void eliminarMovimiento(Integer idMovimiento) {
		repo.deleteById(idMovimiento);
	}

}
