package imb.pr2.stock.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Categoria;
import imb.pr2.stock.repository.CategoriaRepository;
import imb.pr2.stock.service.ICategoriaService;

@Service
public class CategoriaServiceImplJpa implements ICategoriaService{
	
	@Autowired
	CategoriaRepository repo;
	
	@Override
	public void setCategoria(Categoria categoria) {
		repo.save(categoria);
	}
	
	@Override
	public List<Categoria> getCategorias() {
		return repo.findAll();
	}
	
	@Override
    public Categoria getCategoriaById(Integer id) {
        return repo.findById(id).orElse(null);
    }

	
	
	@Override
	public void deleteByIdCategoria(Integer id) {
		repo.deleteById(id);
		
		
	}

	@Override
	public boolean exists(Integer id) {
		return repo.existsById(id);
	}

	


}
