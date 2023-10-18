package imb.pr2.stock.controller;

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

import imb.pr2.stock.entity.Empleado;
import imb.pr2.stock.service.IEmpleadoService;

@RestController
@RequestMapping("/api/v1/Empleado")
public class EmpleadoController {

	@Autowired
	IEmpleadoService service;

	@GetMapping
	
    public ResponseEntity<APIResponse<List<Empleado>>> mostrarTodos() { 
        List<Empleado> proveedores = service.buscar(); 
        if(!proveedores.isEmpty()) {
            return ResponseUtil.success(proveedores); 
        }
        else { 
            return ResponseUtil.notFound("No se encontraron empleados."); 
        }
   }

	@GetMapping("/{id}")
	
	  public ResponseEntity<APIResponse<Empleado>> mostrarEmpleadoPorId(@PathVariable Integer id) {
        if(service.existe(id)) {
            return ResponseUtil.success(service.buscarPorId(id));
        }else {
            return ResponseUtil.notFound("No se encontro el empleado con el id: "+id+".");}
        }

	@PostMapping
	
    public ResponseEntity<APIResponse<Empleado>> crearProveedor(@RequestBody Empleado empleado) {
        if(service.existe((empleado.getId()))) {
            return ResponseUtil.badRequest("Ya existe el proveedor con el id: "+empleado.getId()+".");
        }else {
            return ResponseUtil.created(service.guardar(empleado));
        }
    }

	@PutMapping
	public ResponseEntity<APIResponse<Empleado>> modificarEmpleado(@RequestBody Empleado empleado) {
        if(service.existe(empleado.getId())) {
            return ResponseUtil.success(service.guardar(empleado));
        }
        else {
            return ResponseUtil.badRequest("No existe el proveedor con el id: "+empleado.getId()+".");
        }

    }

	@DeleteMapping("/{id}")

	public ResponseEntity<APIResponse<Empleado>> eliminarEmpleado(@PathVariable("id") Integer id) {
        if(service.existe(id)) {
            Empleado empleadoEliminado = service.buscarPorId(id);
            service.eliminar(id);
            return ResponseUtil.success(empleadoEliminado);

        }else {
            return ResponseUtil.badRequest("No existe el proveedor con el id: "+id+".");
        }

    }

}
