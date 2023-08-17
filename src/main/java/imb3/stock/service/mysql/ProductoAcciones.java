package imb3.stock.service.mysql;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import imb3.stock.repository.ProductorRepository;
import imb3.stock.service.IProductoService;
import imb3.stock.entity.Producto;

@Service
@Primary
public class ProductoAcciones implements IProductoService{

	@Autowired
	ProductorRepository repo;
	
	@Override
	public List<Producto> buscarProductos(){
		return repo.findAll();
	}
	
	@Override
	public Producto buscarProductoPorId (Integer id){
		Optional<Producto> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
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
