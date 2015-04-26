package Model;

import Controller.Contexto;
import Controller.Fecha;
import Controller.Hora;

import java.io.Serializable;


public class Tarea implements Serializable {
	
	public enum estado implements Serializable{activo, pausado, terminado}
	private int id;
	private String nombre;
	private Fecha fi;
	private Fecha ff;
	private Hora hi;
	private Hora hf;
	private String descripcion;
	private estado estado;
	private int color;
	private Contexto contexto;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Fecha getFi() {
		return fi;
	}

	public void setFi(Fecha fi) {
		this.fi = fi;
	}

	public Fecha getFf() {
		return ff;
	}

	public void setFf(Fecha ff) {
		this.ff = ff;
	}

	public Hora getHi() {
		return hi;
	}

	public void setHi(Hora hi) {
		this.hi = hi;
	}

	public Hora getHf() {
		return hf;
	}

	public void setHf(Hora hf) {
		this.hf = hf;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public estado getEstado() {
		return estado;
	}

	public void setEstado(estado estado) {
		this.estado = estado;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}


	
	//Creación: se crea completa o vacía y se modifican los datos individualmente con los setters

	public Tarea(int id, String nombre, Fecha fi, Fecha ff, Hora hi, Hora hf, String descripcion, int color, Contexto contexto)
	{
		this.id = id;
		this.nombre = nombre;
		this.fi = fi;
		this.ff = ff;
		this.hi = hi;
		this.hf = hf;
		this.descripcion = descripcion;
		this.estado = estado.activo;
		this.color = color;
		this.contexto = contexto;
	}
	
	public Tarea(int id)
	{
		this.id = id;
		this.nombre = "Model.Tarea"+Integer.toString(id);
		this.fi = null;
		this.ff = null;
		this.hi = null;
		this.hf = null;
		this.descripcion = null;
		this.estado = estado.pausado;
		this.color = 0;
		this.contexto = null;
	}
	
	public Tarea(Tarea t)
	{
		this.nombre = t.getNombre();
		this.fi = t.getFi();
		this.ff = t.getFf();
		this.hi = t.getHi();
		this.hf = t.getHf();
		this.descripcion = t.getDescripcion();
		this.estado = t.getEstado();
		this.color = t.getColor();
		this.contexto = t.getContexto();
	}
}