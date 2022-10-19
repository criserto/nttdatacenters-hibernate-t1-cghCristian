package org.example.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT", schema = "client")
public class Client {
//	identificador
//	numérico, nombre, primer apellido, segundo apellido, y número de documento de identidad (único, no nulo y
//	hasta 9 caracteres

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "NAME")
	private String nombre;

	@Column(name = "APELLIDO1")
	private String apellido1;

	@Column(name = "APELLIDO2")
	private String apellido2;

	@Column(name = "DNI", length = 9, nullable = false)
	private String dni;

	public Client() {
		super();
	}

	public Client(Integer id, String nombre, String apellido1, String apellido2, String dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", dni=" + dni + "]";
	}

}
