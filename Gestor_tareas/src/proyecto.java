import java.util.ArrayList;


public class proyecto {
	public enum estado{activo, pausado, terminado}
	private int id;
	private String nombre;
	private ArrayList<tarea> tareas;
	private estado estado;
	
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
	
	public ArrayList<tarea> getTareas() {
		return tareas;
	}
	
	public void setTareas(ArrayList<tarea> tareas) {
		this.tareas = tareas;
	}
	
	public estado getEstado(){
		return estado;
	}
	
	public void setEstado(estado estado){
		this.estado = estado;
	}
	
	public proyecto(int id)
	{
		this.id = id;
		this.nombre = nombre;
		this.tareas = new ArrayList<tarea>();
	}
}
