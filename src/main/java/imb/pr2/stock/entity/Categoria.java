package imb.pr2.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Categoria{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;
	
	private boolean habilitado;
	
	//id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//habilitado
	public boolean isHabilitado() {
		return habilitado;
	}
	
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
}
