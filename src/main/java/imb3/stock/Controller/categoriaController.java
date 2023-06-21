package imb3.stock.Controller;

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

import imb3.stock.entity.categorias;
import imb3.stock.service.categoriaInterface;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/categorias")
public class categoriaController {
	
	@Autowired
	categoriaInterface servicio;
	
	@PostMapping("/{nombre}")
	public ResponseEntity<APIResponse<List<categorias>>> postProcessor(@PathVariable String nombre) {
		servicio.setCategoria(nombre);
		List<String> message = new ArrayList<>();
		message.add("Categoria creada correctamente.");
		APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(HttpStatus.OK.value(),message,servicio.getCategorias());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<APIResponse<List<categorias>>> getProcessor() {
		if(servicio.getCategorias() != null) {
			List<String> message = new ArrayList<>();
			message.add("Categorias obtenidas correctamente.");
			APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(HttpStatus.OK.value(),message,servicio.getCategorias());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("No existen categorias.");
			APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(HttpStatus.BAD_REQUEST.value(),message,servicio.getCategorias());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> getByIdProcessor(@PathVariable Integer id) {
		if(this.existeId(id) == true) {
			List<String> message = new ArrayList<>();
			message.add("Categoria obtenida correctamente.");
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.OK.value(),message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("El ID: "+id+" no existe.");
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.BAD_REQUEST.value(),message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		}
		
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> putProcessor(@PathVariable Integer id,@RequestBody categorias categoria) {
		if(this.existeId(id) == true) {
			List<String> message = new ArrayList<>();
			message.add("Categoria modificada correctamente");
			servicio.modifyCategoria(id,categoria);
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.OK.value(),message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("El ID: "+id+" no existe.");
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.BAD_REQUEST.value(),message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> deleteProcessor(@PathVariable Integer id) {
		if(this.existeId(id) == true) {
			List<String> message = new ArrayList<>();
			message.add("Categoria eliminada correctamente");
			categorias categoriasEliminada = servicio.getCategoriaById(id);
			servicio.deleteByIdCategoria(id);
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.OK.value(),message,categoriasEliminada);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> message = new ArrayList<>();
			message.add("El ID: "+id+" no existe.");
			APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.BAD_REQUEST.value(),message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	public boolean existeId(Integer id) {
		if (id==null) {
			return false;
		}else {
			categorias categoria = servicio.getCategoriaById(id);
			if (categoria == null) {
				return false;
			}else {
				return true;
			}
			
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<categorias> response = new APIResponse<categorias>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
	
	

}
