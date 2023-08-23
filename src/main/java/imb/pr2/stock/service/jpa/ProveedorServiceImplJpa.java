package imb.pr2.stock.service.jpa;

import java.util.List;
import java.util.Optional;

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
	public List<Proveedor> buscarProveedores() {
		
		return repo.findAll();
	}

	@Override
	public Proveedor buscarProveedorPorId(Integer id) {
			Optional<Proveedor> optional = repo.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				return null;
			}
		
	}

	@Override
	public void guardarProveedor(Proveedor proveedor) {
		repo.save(proveedor);
		
	}

	@Override
	public void eliminarProveedor(Integer id) {
		repo.deleteById(id);
		
		
	}

}
