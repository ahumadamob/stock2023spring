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

import imb.progra.arq.controllers.APIResponse;
import imb.progra.arq.entity.Categoria;
import imb3.stock.Service.IMovimientoService;
import imb3.stock.entity.MovimientoStock;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

//localhost:8080/api/imb3/movimiento

@RestController
@RequestMapping("/api/imb3/movimientos")
public class MovimientoStockController {
	
	@Autowired
	IMovimientoService service;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<MovimientoStock>>> mostrarTodos(){
		APIResponse<List<MovimientoStock>> response = new APIResponse<List<MovimientoStock>>(200, null, service.buscarMovimientos());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<MovimientoStock>> mostrarMovimientoPorId(@PathVariable("id") Integer id){
		if(this.existe(id)) {
			MovimientoStock movimiento = service.buscarMovimientoPorId(id);
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.OK.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el movimiento con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<MovimientoStock>> crearMovimiento(@RequestBody MovimientoStock movimiento) {
		if(this.existe(movimiento.getIdMovimiento())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un movimiento con el id = " + movimiento.getIdMovimiento().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			service.guardarMovimiento(movimiento);
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.CREATED.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<MovimientoStock>> modificarMovimiento(@RequestBody MovimientoStock movimiento) {
		if(this.existe(movimiento.getIdMovimiento())) {
			service.guardarMovimiento(movimiento);
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.OK.value(), null, movimiento);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un movimiento con el id especificado");
			messages.add("Para crear uno nuevo utilice el verbo POST");
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<MovimientoStock>> eliminarMovimiento(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			service.eliminarMovimiento(id);
			List<String> messages = new ArrayList<>();
			messages.add("El movimiento que figura en el cuerpo ha sido eliminado") ;			
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un movimiento con el id = " + id.toString());
			APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			MovimientoStock movimiento = service.buscarMovimientoPorId(id);
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
		APIResponse<MovimientoStock> response = new APIResponse<MovimientoStock>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}

}
