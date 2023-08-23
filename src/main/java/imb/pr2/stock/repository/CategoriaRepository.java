package imb.pr2.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.stock.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
