package imb3.stock.controller;

import java.util.ArrayList;
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

import imb3.stock.controller.APIResponse;
import imb3.stock.entity.Producto;
import imb3.stock.service.IProductoService;

@RestController
@RequestMapping("/api/v1/Producto")
public class Controller {

	@Autowired
	IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Producto>>> mostrarTodos() {		
		APIResponse<List<Producto>> response = new APIResponse<List<Producto>>(200, null, productoService.buscarProductos());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Producto>> mostrarProductoPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Producto categoria = productoService.buscarProductoPorId(id);
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.OK.value(), null, categoria);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el producto con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Producto>> crearCategoria(@RequestBody Producto producto) {
		if(this.existe(producto.getIdProducto())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un producto con el ID = " + producto.getIdProducto().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			productoService.guardarProducto(producto);
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.CREATED.value(), null, producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Producto>> modificarCategoria(@RequestBody Producto producto) {
		if(this.existe(producto.getIdProducto())) {
			productoService.guardarProducto(producto);
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.OK.value(), null, producto);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una categoria con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Producto>> eliminarCategoria(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			productoService.eliminarProducto(id);
			List<String> messages = new ArrayList<>();
			messages.add("El producto que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un producto con el ID = " + id.toString());
			APIResponse<Producto> response = new APIResponse<Producto>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Producto producto = productoService.buscarProductoPorId(id);
			if(producto == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
}
