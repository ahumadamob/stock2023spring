package imb3.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.stock.entity.categorias;

public interface repositorio extends JpaRepository<categorias, Integer> {

}
