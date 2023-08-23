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

import imb.pr2.stock.entity.Movimiento;
import imb.pr2.stock.service.IMovimientoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

//localhost:8080/api/imb3/movimiento

@RestController
@RequestMapping("/api/imb3/movimientos")
public class MovimientoController {
	
	@Autowired
	IMovimientoService service;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Movimiento>>> mostrarTodos(){
		APIResponse<List<Movimiento>> response = new APIResponse<List<Movimiento>>(200, null, service.buscarMovimientos());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Movimiento>> mostrarMovimientoPorId(@PathVariable("id") Integer id){
		if(this.existe(id)) {
			Movimiento movimiento = service.buscarMovimientoPorId(id);
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.OK.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el movimiento con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Movimiento>> crearMovimiento(@RequestBody Movimiento movimiento) {
		if(this.existe(movimiento.getIdMovimiento())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un movimiento con el id = " + movimiento.getIdMovimiento().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			service.guardarMovimiento(movimiento);
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.CREATED.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Movimiento>> modificarMovimiento(@RequestBody Movimiento movimiento) {
		if(this.existe(movimiento.getIdMovimiento())) {
			service.guardarMovimiento(movimiento);
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.OK.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un movimiento con el id especificado");
			messages.add("Para crear uno nuevo utilice el verbo POST");
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Movimiento>> eliminarMovimiento(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			service.eliminarMovimiento(id);
			List<String> messages = new ArrayList<>();
			messages.add("El movimiento que figura en el cuerpo ha sido eliminado") ;			
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un movimiento con el id = " + id.toString());
			APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Movimiento movimiento = service.buscarMovimientoPorId(id);
			if(movimiento == null) {
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
		APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}

}
