package imb.pr2.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.stock.entity.Proveedor;
import imb.pr2.stock.service.IProveedorService;
import jakarta.validation.ConstraintViolationException;


@RestController 
@RequestMapping("/api/v1/Proveedor")
public class ProveedorController {
	
	@Autowired
	IProveedorService proveedorService;

 	@GetMapping //Anotacion Usada para indicar que el metodo siguiende debe ejecutarse al ser llamado el GET.
 	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarTodos() { //Metodo publico que devulve un objeto del tipo ResponseEntity<APIResponse<List<Proveedor>>> llamado mostrarTodos.
 		List<Proveedor> proveedores = proveedorService.buscar(); //Creacion de una variable del tipo List<Proveedor> llamada proveedores, indicando que es igual a una lista de proveedores si es que existe si no el valor de la variable sera null.
 		if(!proveedores.isEmpty()) { //Comprobarion si NO esta vacio, de la lista de proveedores almacenada en la variable proveedores.
 			return ResponseUtil.success(proveedores); //Devolucion del metodo si la lista de proveedores no estaba vacia.
 		}
 		else { //Bloque de codigo a ejecutarse si falla la comprobacion anterior.
 			return ResponseUtil.notFound("No se encontraron proveedores."); //Devolucion del metodo si la lista de proveedores si estaba vacia.
 		}
	}
 	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Proveedor>> mostrarProveedorPorId(@PathVariable Integer id) {
		if(proveedorService.exists(id)) {
			return ResponseUtil.success(proveedorService.buscarPorId(id));
		}else {
			return ResponseUtil.notFound("No se encontro el proveedor con el id: "+id+".");	
		}
	
	}
 	@PostMapping
    public ResponseEntity<APIResponse<Proveedor>> crearProveedor(@RequestBody Proveedor proveedor) {
        if(proveedorService.exists((proveedor.getId()))) {
            return ResponseUtil.badRequest("Ya existe el proveedor con el id: "+proveedor.getId()+".");
        }else {
            return ResponseUtil.created(proveedorService.guardar(proveedor));
        }
    }
 	@PutMapping	
	public ResponseEntity<APIResponse<Proveedor>> modificarProveedor(@RequestBody Proveedor proveedor) {
		if(proveedorService.exists(proveedor.getId())) {
			return ResponseUtil.success(proveedorService.guardar(proveedor));
		}
		else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+proveedor.getId()+".");
		}

	}
 	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Proveedor>> eliminarProveedor(@PathVariable("id") Integer id) {
		if(proveedorService.exists(id)) {
			Proveedor proveedorEliminado = proveedorService.buscarPorId(id);
			proveedorService.eliminar(id);
			return ResponseUtil.success(proveedorEliminado);
			
		}else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+id+".");		
		}
		
	}
 	
 	@ExceptionHandler(ConstraintViolationException.class)
 	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
 		return ResponseUtil.handleConstraintException(ex);
 	}
 }