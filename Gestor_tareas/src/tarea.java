import java.io.Serializable;


public class tarea implements Serializable {
	
	public enum estado implements Serializable{activo, pausado, terminado}
	private int id;
	private String nombre;
	private fecha fi;
	private fecha ff;
	private hora hi;
	private hora hf;
	private String descripcion;
	private estado estado;
	private int color;
	private contexto contexto;
	
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

	public fecha getFi() {
		return fi;
	}

	public void setFi(fecha fi) {
		this.fi = fi;
	}

	public fecha getFf() {
		return ff;
	}

	public void setFf(fecha ff) {
		this.ff = ff;
	}

	public hora getHi() {
		return hi;
	}

	public void setHi(hora hi) {
		this.hi = hi;
	}

	public hora getHf() {
		return hf;
	}

	public void setHf(hora hf) {
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

	public contexto getContexto() {
		return contexto;
	}

	public void setContexto(contexto contexto) {
		this.contexto = contexto;
	}


	
	//Creación: se crea completa o vacía y se modifican los datos individualmente con los setters

	public tarea(int id, String nombre, fecha fi, fecha ff, hora hi, hora hf, String descripcion, int color, contexto contexto)
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
	
	public tarea(int id)
	{
		this.id = id;
		this.nombre = "Tarea"+Integer.toString(id);
		this.fi = null;
		this.ff = null;
		this.hi = null;
		this.hf = null;
		this.descripcion = null;
		this.estado = estado.pausado;
		this.color = 0;
		this.contexto = null;
	}
	
	public tarea(tarea t)
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
