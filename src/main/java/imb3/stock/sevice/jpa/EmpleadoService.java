package imb3.stock.sevice.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;



import imb3.stock.entity.Empleado;
import imb3.stock.repository.EmpleadoRepository;
import imb3.stock.service.IEmpleadoService;

@Service
@Primary
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	EmpleadoRepository repo;
	
	@Override
	public List<Empleado> buscarEmpleados() {
		return repo.findAll();
		
	}

	@Override
	public Empleado buscarEmpleadoPorId(Integer id) {
		Optional<Empleado> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

		

	@Override
	public void guardarEmpleado(Empleado empleado) {
		repo.save(empleado);
			
		}

				
	

	@Override
	public void eliminarEmpleado(Integer id) {
		repo.deleteById(id);

	}

}
