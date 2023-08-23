package imb.pr2.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Producto;

public interface ProductorRepository extends JpaRepository<Producto,Integer>{

}
