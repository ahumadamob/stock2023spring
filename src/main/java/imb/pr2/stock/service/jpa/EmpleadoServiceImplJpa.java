package imb.pr2.stock.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Empleado;
import imb.pr2.stock.repository.EmpleadoRepository;
import imb.pr2.stock.service.IEmpleadoService;

@Service
@Primary
public class EmpleadoServiceImplJpa implements IEmpleadoService {

	@Autowired
	EmpleadoRepository repo;
	
	@Override
	public List<Empleado> buscarEmpleados() {
		return repo.findAll();
		
	}

    @Override
    public Empleado buscarEmpleadoPorId(Integer id) {
        return repo.findById(id).orElse(null);
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
