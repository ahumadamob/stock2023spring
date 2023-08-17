package imb3.stock.servicee.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.stock.Service.IProveedorService;
import imb3.stock.entity.Proveedor;
import imb3.stock.repository.ProveedorRepository;

@Service
@Primary
public class ProveedorService implements IProveedorService {
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
