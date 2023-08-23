package imb.pr2.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

}
