package Model;

import java.io.Serializable;
import java.util.Calendar;


public class Tarea implements Serializable, Comparable<Tarea> {
	
	private int id;
	private String nombre;
	private Fecha fi;
	private Fecha ff;
	private Hora hi;
	private Hora hf;
	private String descripcion;
	private Estado estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void siguienteEstado() {
		if (estado.equals(Estado.activo)) {
			estado = Estado.pausado;
		} else if (estado.equals(Estado.pausado)) {
			estado = Estado.terminado;
		} else if (estado.equals(Estado.terminado)) {
			estado = Estado.activo;
		}
	}

	public void siguienteEstado2() {
		if (estado.equals(Estado.terminado)) {
			estado = Estado.expirado;
		}
		else if (estado.equals(Estado.expirado)) {
			estado = Estado.terminado;
		}
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}


	
	//Creación: se crea completa o vacía y se modifican los datos individualmente con los setters

	public Tarea(int id, String nombre, Fecha fi, Fecha ff, Hora hi, Hora hf, String descripcion, Contexto contexto)
	{
		this.id = id;
		this.nombre = nombre;
		this.fi = fi;
		this.ff = ff;
		this.hi = hi;
		this.hf = hf;
		this.descripcion = descripcion;
		this.contexto = contexto;
		Calendar calendarioActual = Calendar.getInstance();
		if (calendarioActual.compareTo(ff.getCalendario()) > 0) {
			/** fecha limite expirada */
			this.estado = estado.expirado;
		}
		else {
			this.estado = Estado.activo;
		}
	}

	public Tarea(int id)
	{
		this.id = id;
		this.nombre = "Tarea"+Integer.toString(id);
		this.fi = null;
		this.ff = null;
		this.hi = null;
		this.hf = null;
		this.descripcion = null;
		this.estado = estado.expirado;
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
		this.contexto = t.getContexto();
	}

	@Override
	public int compareTo(Tarea o) {
		/** compareTo debe ser una relacion simetrica, reflexiva y transitiva **/
		/** r(a,b) == r(b,a)
		 * r(a,a)
		 * r(a,b) && r(b,c) ==> r(a,c)
		 */
		Calendar calendario1 = getFf().getCalendario();
		Calendar calendario2 = o.getFf().getCalendario();
		/** ajustar a la hora final **/
		calendario1.set(Calendar.SECOND,getHf().getSegundos());
		calendario2.set(Calendar.SECOND,o.getHf().getSegundos());

		int i, j;
		if (calendario1.get(Calendar.YEAR) == calendario2.get(Calendar.YEAR)) {
			i = calendario1.get(Calendar.DAY_OF_YEAR);
			j = calendario2.get(Calendar.DAY_OF_YEAR);
		}
		else {
			i = calendario1.get(Calendar.YEAR);
			j = calendario2.get(Calendar.YEAR);
		}
		return i-j;
	}
	
	public void aplazar(int dias) {
		Calendar c = Calendar.getInstance();
		c.set(ff.y, ff.m-1, ff.d);
		if (dias % 30 == 0) {
			c.add(Calendar.MONTH,dias/30);
		}
		else if (dias % 7 == 0) {
			c.add(Calendar.WEEK_OF_YEAR,dias/7);
		}
		else {
			c.add(Calendar.DAY_OF_MONTH, dias);
		}
		this.setFechaPorCalendario(c);
	}

	public void actualizarEstado() {
		Calendar calendarioActual = Calendar.getInstance();
		if (calendarioActual.compareTo(this.getFf().getCalendario())>0) {
			/** fecha final tarea expirada */
			if (!this.getEstado().equals(Estado.terminado)) {
				/** tarea no terminada */
				this.setEstado(Estado.expirado);
			}
		}
		else {
			/** fecha final tarea no expirada */
			if (this.getEstado().equals(Estado.expirado)) {
				/** tarea expirada + tiempo no expirado => tarea activa */
				this.setEstado(Estado.activo);
			}
		}
	}

	private void setFechaPorCalendario(Calendar calendario) {
		int d = calendario.get(Calendar.DAY_OF_MONTH);
		int m = calendario.get(Calendar.MONTH)+1;
		int y = calendario.get(Calendar.YEAR);
		Fecha newFecha = new Fecha(d, m , y);
		setFf(newFecha);
	}
}
