package imb.pr2.stock.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr2.stock.entity.Categoria;
import imb.pr2.stock.repository.CategoriaRepository;
import imb.pr2.stock.service.ICategoriaInterface;

@Service
public class CategoriaServiceImplJpa implements ICategoriaInterface{
	
	@Autowired
	CategoriaRepository repo;
	
	@Override
	public void setCategoria(String nombre) {
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		Categoria categoria = new Categoria();
		categoria.setNombre(nombre);
		categoriaList.add(categoria);
		repo.saveAll(categoriaList);
	}
	
	@Override
	public List<Categoria> getCategorias() {
		List<Categoria> optional = new ArrayList<Categoria>();
		optional = repo.findAll();
		if(optional.isEmpty()) {
			return null;
		}else {
			return repo.findAll();
		}
		
	}
	
	@Override
	public Categoria getCategoriaById(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
		
	}

	@Override
	public boolean modifyCategoria(Integer id,Categoria categoria) {
		Optional<Categoria> optional = repo.findById(id);
		if(optional.isPresent()) {
			categoria.setId(id);
			repo.save(categoria);
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public boolean deleteByIdCategoria(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		if(optional.isPresent()) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
		
		
	}

	


}
