package Model;
import View.VentanaError;

import java.io.*;
import java.util.ArrayList;

public class Model {

	private int contador_proyectos;
	private int id_tareas;
	private int contador_tareas;
	private int id_proyectos;
	private ArrayList<Proyecto> proyectos;
	public ArrayList<String> getCorreos() {
		return correos;
	}

	public void setCorreos(ArrayList<String> correos) {
		this.correos = correos;
	}

	private ArrayList<Contexto> contextos;
	private ArrayList<String> correos;
	
    // data
    public Model() {
		proyectos = new ArrayList<>();
		contextos = new ArrayList<>();
		correos = new ArrayList<String>();
    }

	public int getContador_proyectos() {
		return contador_proyectos;
	}


	public void setContador_proyectos(int contador_proyectos) {
		this.contador_proyectos = contador_proyectos;
	}


	public int getId_tareas() {
		return id_tareas;
	}


	public void setId_tareas(int id_tareas) {
		this.id_tareas = id_tareas;
	}


	public int getContador_tareas() {
		return contador_tareas;
	}


	public void setContador_tareas(int contador_tareas) {
		this.contador_tareas = contador_tareas;
	}


	public int getId_proyectos() {
		return id_proyectos;
	}


	public void setId_proyectos(int id_proyectos) {
		this.id_proyectos = id_proyectos;
	}


	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}


	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}


	public ArrayList<Contexto> getContextos() {
		return contextos;
	}


	public void setContextos(ArrayList<Contexto> contextos) {
		this.contextos = contextos;
	}


	public void agregarTarea(Tarea t, int id_proyecto)
	{
		Proyecto actual_proyecto;
		t.setId(contador_tareas);
		for (int i = 0; i < contador_proyectos; i++) {
			actual_proyecto = proyectos.get(i);
			if(actual_proyecto.getId()==id_proyecto)
			{
				actual_proyecto.getTareas().add(t);
				contador_tareas++;
				id_tareas++;
				return;
			}
		}
	}

	public void agregarContexto(Contexto contexto) {
		contextos.add(contexto);
	}

	public void agregarProyecto(Proyecto p)
	{
		p.setId(id_proyectos);
		proyectos.add(p);
		contador_proyectos++;
		id_proyectos++;
	}

	/** util para debugging **/
	public void printTareas() {
		for (Proyecto p : proyectos) {
			for (Tarea t : p.getTareas()) {
				System.out.print("["+t.getNombre()+"]");
			}
		}
		System.out.println("");
	}

	public void siguienteEstado(int id) {
		Proyecto actual_proyecto;
		Tarea actual_tarea;
		for (int i = 0; i < contador_proyectos; i++) {
			actual_proyecto = proyectos.get(i);
			for (int j = 0; j < actual_proyecto.getTareas().size(); j++) {
				actual_tarea = actual_proyecto.getTareas().get(j);
				if (actual_tarea.getId() == id) {
					actual_tarea.siguienteEstado();
					return;
				}
			}
		}
	}

	public void siguienteEstado2(int id) {
		Proyecto actual_proyecto;
		Tarea actual_tarea;
		for (int i = 0; i < contador_proyectos; i++) {
			actual_proyecto = proyectos.get(i);
			for (int j = 0; j < actual_proyecto.getTareas().size(); j++) {
				actual_tarea = actual_proyecto.getTareas().get(j);
				if (actual_tarea.getId() == id) {
					actual_tarea.siguienteEstado2();
					return;
				}
			}
		}
	}

	public void eliminarTarea(int id) {
		Proyecto actual_proyecto;
		Tarea actual_tarea;
		for (int i = 0; i < contador_proyectos; i++) {
			actual_proyecto = proyectos.get(i);
			for (int j = 0; j < actual_proyecto.getTareas().size(); j++) {
				actual_tarea = actual_proyecto.getTareas().get(j);
				if (actual_tarea.getId() == id) {
					actual_proyecto.getTareas().remove(j);
					contador_tareas--;
					return;
				}
			}
		}
	}

	public void eliminarProyecto(int id)
	{
		for (int i = 0; i < contador_proyectos; i++)
		{
			if(proyectos.get(i).getId()==id)
			{
				proyectos.remove(i);
				contador_proyectos--;
				return;
			}
		}
	}


	public Tarea getTarea(int id)
	{
		Proyecto actual_proyecto;
		Tarea actual_tarea;
		for (int i = 0; i < contador_proyectos; i++)
		{
			actual_proyecto = proyectos.get(i);
			for(int j=0; j<actual_proyecto.getTareas().size(); j++)
			{
				actual_tarea = actual_proyecto.getTareas().get(j);
				if(actual_tarea.getId()==id)
				{
					return actual_tarea;
				}
			}
		}
		return null;
	}


	public Proyecto getProyecto(int id)
	{
		Proyecto actual_proyecto;
		for (int i = 0; i < contador_proyectos; i++)
		{
			actual_proyecto =proyectos.get(i);
			if(actual_proyecto.getId()==id)
			{
				return actual_proyecto;
			}
		}
		return null;
	}


	/** devuelve indice (n� en lista) de
	 *  tarea (indicada por id)
	 *  en la lista de tareas de su proyecto */
	public int indiceTarea(int id)
	{
		Proyecto actual_proyecto;
		for (int i = 0; i < contador_proyectos; i++)
		{
			actual_proyecto = proyectos.get(i);
			for(int j=0; j<actual_proyecto.getTareas().size(); j++)
			{
				if(actual_proyecto.getTareas().get(j).getId()==id)
				{
					return j;
				}
			}
		}
		return -1;
	}

	public int indiceProyecto(int id)
	{
		for (int i = 0; i < contador_proyectos; i++) {
			if(proyectos.get(i).getId()==id)
			{
				return i;
			}
		}
		return -1;
	}


	public boolean exportarTarea(int id, String path_destino)
	{
		Tarea t = new Tarea(getTarea(id));
		t.setId(-1);
		try{
			FileOutputStream fos = new FileOutputStream(path_destino);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(t);
			out.close();
			fos.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}


	public Tarea importarTarea(String path)
	{
		try
		{
			 FileInputStream fis = new FileInputStream(path);
			 ObjectInputStream in = new ObjectInputStream(fis);
			 return (Tarea)in.readObject();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/** intercambia una tarea por otra en la lista de tareas de su proyecto */
	public void cambiarTarea(Tarea tarea) {
		/** iteramos por cada proyecto */
		for (Proyecto p : proyectos) {
			/** iteramos por cada tarea */
			for (Tarea t : p.getTareas()) {
				if (t.getId() == tarea.getId()) {
					/** indice = n� en lista de la tarea en su proyecto */
					int indice = indiceTarea(t.getId());
					/** si las tareas tienen el mismo id (osea son la misma), intercambiar */
					p.getTareas().set(indice, tarea);
				}
			}
		}
	}

	/** elimina un contexto y cambia las tareas asociadas a el contexto miscelaneo */
	public void eliminarContexto(Contexto contexto) {
		if (contexto.getNombre().equals("miscelaneo")) {
			VentanaError VE = new VentanaError("No se puede borrar el contexto \"miscelaneo\"");
			return;
		}
		/** obtener contexto miscelaneo */
		Contexto misc = null;
		for (Contexto c : contextos) {
			if (c.getNombre().equals("miscelaneo")) {
				misc = c;
				break;
			}
		}
		if (misc == null) {
			VentanaError VE = new VentanaError("Error borrando contexto");
			return;
		}
		/** cambiar tareas con contexto "contexto" a "miscelaneo" */
		for (Proyecto p : proyectos) {
			for (Tarea t : p.getTareas()) {
				if (t.getContexto().equals(contexto)) {
					t.setContexto(misc);
				}
			}
		}
		/** eliminar Contexto "contexto" */
		/*
		for (Contexto c : contextos) {
			if (c.equals(contexto)) {
				System.out.println("eliminando contexto "+contexto.getNombre());
				contextos.remove(c);
			}
		}
		*/
		contextos.remove(contexto);

	}
}
