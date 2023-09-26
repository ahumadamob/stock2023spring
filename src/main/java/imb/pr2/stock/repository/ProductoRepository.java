package imb.pr2.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{

}
