package imb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
