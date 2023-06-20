package imb3.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.stock.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
