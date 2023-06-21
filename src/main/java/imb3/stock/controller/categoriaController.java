package imb3.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.stock.entity.categorias;
import imb3.stock.service.categoriaInterface;

@RestController
@RequestMapping("/api/v1/categorias")
public class categoriaController {
	
	@Autowired
	categoriaInterface servicio;
	
	@PostMapping("/{nombre}")
	public ResponseEntity<APIResponse<List<categorias>>> postProcessor(@PathVariable String nombre) {
		servicio.setCategoria(nombre);
		String message = "Categoria creada correctamente.";
		APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(200,message,servicio.getCategorias());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	public ResponseEntity<APIResponse<List<categorias>>> getProcessor() {
		if(servicio.getCategorias() != null) {
			String message = "Categorias obtenidas correctamente.";
			APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(200,message,servicio.getCategorias());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			String message = "No existen categorias.";
			APIResponse<List<categorias>> response = new APIResponse<List<categorias>>(400,message,servicio.getCategorias());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> getByIdProcessor(@PathVariable Integer id) {
		if(servicio.getCategoriaById(id) != null) {
			String message = "Categoria obtenida correctamente.";
			APIResponse<categorias> response = new APIResponse<categorias>(200,message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			String message = "El ID: "+id+" no existe.";
			APIResponse<categorias> response = new APIResponse<categorias>(400,message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		}
		
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> putProcessor(@PathVariable Integer id,@RequestBody categorias categoria) {
		boolean responseBool = servicio.modifyCategoria(id,categoria);
		if(responseBool == true) {
			String message = "Categoria modificada correctamente";
			APIResponse<categorias> response = new APIResponse<categorias>(200,message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			String message = "El ID: "+id+" no existe.";
			APIResponse<categorias> response = new APIResponse<categorias>(400,message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<categorias>> deleteProcessor(@PathVariable Integer id) {
		categorias categoriasEliminada = servicio.getCategoriaById(id);
		boolean responseBool = servicio.deleteByIdCategoria(id);
		if(responseBool == true) {
			String message = "Categoria eliminada correctamente";
			APIResponse<categorias> response = new APIResponse<categorias>(200,message,categoriasEliminada);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			String message = "El ID: "+id+" no existe.";
			APIResponse<categorias> response = new APIResponse<categorias>(400,message,servicio.getCategoriaById(id));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	

}
