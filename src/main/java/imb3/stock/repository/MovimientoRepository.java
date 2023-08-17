package imb3.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.stock.entity.MovimientoStock;

public interface MovimientoRepository extends JpaRepository<MovimientoStock, Integer> {

}
