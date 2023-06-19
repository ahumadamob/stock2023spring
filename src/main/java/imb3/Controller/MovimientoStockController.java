package imb3.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.Service.IMovimientoService;
import imb3.entity.MovimientoStock;

//localhost:8080/api/imb3/movimiento

@RestController
@RequestMapping("/api/imb3/movimientos")
public class MovimientoStockController {
	
	@Autowired
	private IMovimientoService service;
	
	@GetMapping
	public List<MovimientoStock> verTodo(){
		return service.buscarMovimientos();
	}
	@PostMapping("/productos")
	public MovimientoStock Crear(@RequestBody MovimientoStock movimiento){
		service.guardarMovimiento(movimiento);
		return movimiento;
	}

}
