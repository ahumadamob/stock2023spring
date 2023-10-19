package imb.pr2.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.stock.entity.Categoria;
import imb.pr2.stock.service.ICategoriaService;
import imb.pr2.stock.service.jpa.CategoriaServiceImplJpa;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
	
	@Autowired
	ICategoriaService servicio;
	
	@PostMapping
	public ResponseEntity<APIResponse<Categoria>> guardar(@RequestBody Categoria categoria){
		if (servicio.existe(categoria.getId())) {
			return ResponseUtil.badRequest("Ya existe esta Categoria");
		}else {
			return ResponseUtil.created(servicio.guardar(categoria));
		}
	}
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Categoria>>> buscarTodos(){
		List<Categoria> categorias = servicio.buscarTodos();
		if (categorias.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron categorias");
		}else {
			return ResponseUtil.success(categorias);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> buscarPorId(@PathVariable Integer id){
		Categoria categoria = servicio.buscarPorId(id);
		if(servicio.existe(id)) {
			return ResponseUtil.success(categoria);
		}else {
			return ResponseUtil.notFound("No se encontro la categoria por el ID");
		}
	}

		
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> putProcessor(@PathVariable Integer id,@RequestBody Categoria categoria) {
		boolean responseBool = servicio.modifyCategoria(id,categoria);
		if(responseBool) {
			List<String> message = new ArrayList<>();
			message.add("Categoria modificada correctamente");
			APIResponse<Categoria> response = new APIResponse<Categoria>(HttpStatus.OK.value(),message,servicio.buscarPorId(id));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("El ID: "+id+" no existe.");
			APIResponse<Categoria> response = new APIResponse<Categoria>(HttpStatus.BAD_REQUEST.value(),message,servicio.buscarPorId(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> deleteProcessor(@PathVariable Integer id) {
		if(servicio.existe(id)) {
			List<String> message = new ArrayList<>();
			message.add("Categoria eliminada correctamente");
			Categoria categoriasEliminada = servicio.buscarPorId(id);
			servicio.eliminar(id);
			APIResponse<Categoria> response = new APIResponse<Categoria>(HttpStatus.OK.value(),message,categoriasEliminada);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("El ID: "+id+" no existe.");
			APIResponse<Categoria> response = new APIResponse<Categoria>(HttpStatus.BAD_REQUEST.value(),message,servicio.buscarPorId(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Categoria> response = new APIResponse<Categoria>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
	
	

}