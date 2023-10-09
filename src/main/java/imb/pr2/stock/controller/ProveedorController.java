package imb.pr2.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController 
@RequestMapping("/api/v1/Proveedor")
public class ProveedorController {
	
	@Autowired
	IProveedorService proveedorService;

 	@GetMapping
 	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarTodos() {
 		List<Proveedor> proveedorTmp = proveedorService.buscarProveedores();
 		if(!proveedorTmp.isEmpty()) {
 			List<String> messages = new ArrayList<>();
 			messages.add("Lista de proveedores encontrados.");
 			APIResponse<List<Proveedor>> response = new APIResponse<List<Proveedor>>(200, messages, proveedorService.buscarProveedores());
 			return ResponseEntity.status(HttpStatus.OK).body(response);
 		}
 		else {
 			List<String> messages = new ArrayList<>();
 			messages.add("No existen proveedores.");
 			APIResponse<List<Proveedor>> response = new APIResponse<List<Proveedor>>(200, messages, proveedorService.buscarProveedores());
 			return ResponseEntity.status(HttpStatus.OK).body(response);		
 		}
	}
 	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Proveedor>> mostrarProveedorPorId(@PathVariable Integer id) {
		if(proveedorService.existe(id)) {
			List<String> messages = new ArrayList<>();
			messages.add("Proveedor con id:" + id.toString() + " encontrado.");
			Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), messages, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el proveedor con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
 	@PostMapping
    public ResponseEntity<APIResponse<Proveedor>> crearProveedor(@RequestBody Proveedor proveedor) {
        if(proveedorService.existe((proveedor.getId()))) {
            List<String> messages = new ArrayList<>();
            messages.add("Ya existe un proveedor con el id = " + proveedor.getId().toString());
            messages.add("Para actualizar utilice el verbo PUT");
            APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else {
            proveedorService.guardarProveedor(proveedor);
            APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.CREATED.value(), null, proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }
 	@PutMapping	
	public ResponseEntity<APIResponse<Proveedor>> modificarProveedor(@RequestBody Proveedor proveedor) {
		if(proveedorService.existe(proveedor.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Proveedor modificado con exito.");
			proveedorService.guardarProveedor(proveedor);
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), messages, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un proveedor con el ID especificado");
			messages.add("Para crear un nuevo proveedor utilice el verbo POST");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
 	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Proveedor>> eliminarProveedor(@PathVariable("id") Integer id) {
		if(proveedorService.existe(id)) {
			List<String> messages = new ArrayList<>();
			messages.add("El proveedor que figura en el cuerpo ha sido eliminado") ;			
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), messages, proveedorService.buscarProveedorPorId(id));
			proveedorService.eliminarProveedor(id);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe el proveedor con el ID = " + id.toString());
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
 	
 	@ExceptionHandler(ConstraintViolationException.class)
 	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
 		List<String> errors = new ArrayList<>();
 		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
 			errors.add(violation.getMessage());
 		}
 		APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), errors, null);
 		return ResponseEntity.badRequest().body(response);
 	}
 }