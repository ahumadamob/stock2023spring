package imb.pr2.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	public Empleado getByDni (Integer dni);
}
