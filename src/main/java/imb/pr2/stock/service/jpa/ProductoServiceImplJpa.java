package imb.pr2.stock.service.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Producto;
import imb.pr2.stock.repository.ProductorRepository;
import imb.pr2.stock.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductoServiceImplJpa implements IProductoService{

	@Autowired
	ProductorRepository repo;
	
	@Override
	public List<Producto> buscarProductos(){
		return repo.findAll();
	}
	
	@Override
	public Producto buscarProductoPorId (Integer id){
		return repo.findById(id).orElse(null);		
	}
	
	@Override
	public void guardarProducto(Producto producto){
		repo.save(producto);
	}
	
	@Override
	public void eliminarProducto(Integer id) {
		repo.deleteById(id);
	}
}
