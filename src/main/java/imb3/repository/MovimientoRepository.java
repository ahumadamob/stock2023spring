package imb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.entity.MovimientoStock;

public interface MovimientoRepository extends JpaRepository<MovimientoStock, Integer> {

}
