package imb3.stock.Controller;

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

import imb3.stock.Service.IProveedorService;
import imb3.stock.entity.Proveedor;


@RestController 
@RequestMapping("/api/v1/Proveedor")
public class ProveedorController {
	
	@Autowired
	IProveedorService proveedorService;

 	@GetMapping
 	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarTodos() {		
		APIResponse<List<Proveedor>> response = new APIResponse<List<Proveedor>>(200, null, proveedorService.buscarProveedores());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
 	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Proveedor>> mostrarProveedorPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), null, proveedor);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Categoría con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
 	@PostMapping
    public ResponseEntity<APIResponse<Proveedor>> crearProveedor(@RequestBody Proveedor proveedor) {
        if(this.existe(proveedor.getId())) {
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
		if(this.existe(proveedor.getId())) {
			proveedorService.guardarProveedor(proveedor);
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), null, proveedor);
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
		if(this.existe(id)) {
			proveedorService.eliminarProveedor(id);
			List<String> messages = new ArrayList<>();
			messages.add("El proveedor que figura en el cuerpo ha sido eliminado") ;			
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una categoria con el ID = " + id.toString());
			APIResponse<Proveedor> response = new APIResponse<Proveedor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
 	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
			if(proveedor == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
 }