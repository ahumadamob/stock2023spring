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
	
	//post
	@PostMapping
	public ResponseEntity<APIResponse<Categoria>> guardarCategoria(@RequestBody Categoria categoria){
		if (servicio.existe(categoria.getId())) {
			return ResponseUtil.badRequest("Ya existe esta Categoria");
		}else {
			return ResponseUtil.created(servicio.guardar(categoria));
		}
	}
	
	//get
	@GetMapping
	public ResponseEntity<APIResponse<List<Categoria>>> buscarCategoriaTodos(){
		List<Categoria> categorias = servicio.buscarTodos();
		if (categorias.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron categorias");
		}else {
			return ResponseUtil.success(categorias);
		}
	}
	
	//getid
	@GetMapping("/{id}")//Utilizaremos la anotacion "getmapping" para hacer darle una funcion a la funcion get de nuestro http
	//Agregaremos "("/{id}")" para buscar en la direccion de la ID que utilizaremos en el postman
	
	//damos un acceso publico, Su entorno sera con las entidades y utilizando la apiresponse en categoria
	//Utilizaremos @pathvarible para poder utilizar la ID directamente en la URL como un parametro de tipo integer que (en este caso se llama id)
	public ResponseEntity<APIResponse<Categoria>> buscarCategoriaPorId(@PathVariable Integer id){ 
		//creamos un objeto llamado categoria y declaramos su valor, que sera igual a los datos de las celdas utilizadas en la tabla de su base de datos
		//vinculadas por su id
		Categoria categoria = servicio.buscarPorId(id);
		//Cromprobamos si la id pertenes a la base de datos
		if(servicio.existe(id)) {//si existe devolvera los valores de las celdas de la base de datos
			return ResponseUtil.success(categoria);
		}else {//Si no existe devolvera el mensaje, "No se encontro la categoria por el ID
			return ResponseUtil.notFound("No se encontro la categoria por el ID");
		}
	}

	//put
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> modificarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){
		if (servicio.existe(categoria.getId())) {
			servicio.guardar(categoria);
			return ResponseUtil.success(categoria);
		}else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> eliminarCategoria(@PathVariable("id") Integer id){ 
		if (servicio.existe(id)) {
			Categoria categoria = servicio.buscarPorId(id);
			servicio.eliminar(id);
			return ResponseUtil.success(categoria);
		}else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}