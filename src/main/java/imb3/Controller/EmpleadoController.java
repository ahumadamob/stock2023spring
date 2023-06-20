package imb3.Controller;

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


import imb3.entity.Empleado;
import imb3.service.IEmpleadoService;

@RestController
@RequestMapping("/api/v1/Empleado")
public class EmpleadoController {

	@Autowired
	IEmpleadoService service;

	@GetMapping
	public ResponseEntity<APIResponse<List<Empleado>>> mostrarTodos() {
		APIResponse<List<Empleado>> response = new APIResponse<List<Empleado>>(200, null, service.buscarEmpleados());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Empleado>> mostrarEmpleadoPorId(@PathVariable("id") Integer id) {
		if (this.existe(id)) {
			Empleado empleado = service.buscarEmpleadoPorId(id);
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.OK.value(), null, empleado);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el Empleado con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping
	public ResponseEntity<APIResponse<Empleado>> crearEmpleado(@RequestBody Empleado empleado) {
		if (this.existe(empleado.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un empleado con el ID = " + empleado.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			service.guardarEmpleado(empleado);
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.CREATED.value(), null, empleado);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Empleado>> modificarEmpleado(@RequestBody Empleado empleado) {
		if(this.existe(empleado.getId())) {
			service.guardarEmpleado(empleado);
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.OK.value(), null, empleado);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un empleado con el ID especificado");
			messages.add("Para crear uno nuevo utilice el verbo POST");
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Empleado>> eliminarEmpleado(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			service.eliminarEmpleado(id);
			List<String> messages = new ArrayList<>();
			messages.add("El Empleado que figura en el cuerpo ha sido eliminado") ;			
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un empleado con el ID = " + id.toString());
			APIResponse<Empleado> response = new APIResponse<Empleado>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}


	private boolean existe(Integer id) {
		if (id == null) {
			return false;
		} else {
			Empleado empleado = service.buscarEmpleadoPorId(id);
			if (empleado == null) {
				return false;
			} else {
				return true;
			}
		}
	}

}
