package imb3.stock.service_jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb3.stock.entity.categorias;
import imb3.stock.repository.repositorio;
import imb3.stock.service.categoriaInterface;

@Service
public class categoriaJpa implements categoriaInterface{
	
	@Autowired
	repositorio repo;
	
	@Override
	public void setCategoria(String nombre) {
		List<categorias> categoriaList = new ArrayList<categorias>();
		categorias categoria = new categorias();
		categoria.setNombre(nombre);
		categoriaList.add(categoria);
		repo.saveAll(categoriaList);
	}
	
	@Override
	public List<categorias> getCategorias() {
		List<categorias> optional = new ArrayList<categorias>();
		optional = repo.findAll();
		if(optional.isEmpty()) {
			return null;
		}else {
			return repo.findAll();
		}
		
	}
	
	@Override
	public categorias getCategoriaById(Integer id) {
		Optional<categorias> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
		
	}

	@Override
	public boolean modifyCategoria(Integer id,categorias categoria) {
		Optional<categorias> optional = repo.findById(id);
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
		Optional<categorias> optional = repo.findById(id);
		if(optional.isPresent()) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
		
		
	}

	


}
