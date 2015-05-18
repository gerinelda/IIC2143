package Model;
import java.io.*;
import java.util.ArrayList;

public class Model {

	private int contador_proyectos;
	private int id_tareas;
	private int contador_tareas;
	private int id_proyectos;
	private ArrayList<Proyecto> proyectos;
	private ArrayList<Contexto> contextos;

    // data
    public Model() {
		proyectos = new ArrayList<>();
		contextos = new ArrayList<>();
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

	public void cambiarTarea(Tarea tarea) {
		for (Proyecto p : proyectos) {
			for (Tarea t : p.getTareas()) {
				int indice = indiceTarea(t.getId());
				if (t.getId() == tarea.getId()) {
					// si la tarea tiene el mismo id entonces intercambiar
					p.getTareas().set(indice, tarea);
				}
			}
		}
	}
}
