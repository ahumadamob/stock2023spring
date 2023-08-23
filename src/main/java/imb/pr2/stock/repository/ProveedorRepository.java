package imb.pr2.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
