package imb.pr2.stock.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Proveedor;
import imb.pr2.stock.repository.ProveedorRepository;
import imb.pr2.stock.service.IProveedorService;

@Service
@Primary
public class ProveedorServiceImplJpa implements IProveedorService {
	@Autowired
	ProveedorRepository repo;
	@Override
	public List<Proveedor> buscar() {
		
		return repo.findAll();
	}

	@Override
    public Proveedor buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public Proveedor guardar(Proveedor proveedor) {
		return repo.save(proveedor);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
		
		
	}

	@Override
	public Boolean exists(Integer id) {
		if (id == null) {
			return false;
		}else {
			return repo.existsById(id);
		}
	}

}
