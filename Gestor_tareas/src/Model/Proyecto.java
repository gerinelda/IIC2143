package Model;

import java.util.ArrayList;


public class Proyecto {
	//public enum Model.Estado
	private int id;
	private String nombre;
	private ArrayList<Tarea> tareas;
	private Estado estado;
	
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
	
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	
	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	public Estado getEstado(){
		return estado;
	}
	
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Proyecto(int id)
	{
		this.id = id;
		this.nombre = "Proyecto "+Integer.toString(id);
		this.tareas = new ArrayList<>();
		this.estado = estado.activo;
	}
	
	public Proyecto(int id, String nombre, Estado estado)
	{
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.tareas = new ArrayList<>();
	}

	@Override
	public String toString() {
		return nombre;
	}
}
