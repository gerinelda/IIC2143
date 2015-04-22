import java.text.BreakIterator;
import java.util.ArrayList;


public class Gestor {
	
	private int contador_proyectos;
	private int id_tareas;
	private int contador_tareas;
	private int id_proyectos;
	private ArrayList<proyecto> proyectos;
	private contexto[] contextos;
	
	
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


	public ArrayList<proyecto> getProyectos() {
		return proyectos;
	}


	public void setProyectos(ArrayList<proyecto> proyectos) {
		this.proyectos = proyectos;
	}


	public contexto[] getContextos() {
		return contextos;
	}


	public void setContextos(contexto[] contextos) {
		this.contextos = contextos;
	}

	
	public void agregarTarea(tarea t, int id_proyecto)
	{
		proyecto actual_proyecto;
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

	public void agregarProyecto(proyecto p)
	{
		p.setId(id_proyectos);
		proyectos.add(p);
		contador_proyectos++;
		id_proyectos++;
	}
	
	
	public void eliminarTarea(int id)
	{
		proyecto actual_proyecto;
		tarea actual_tarea;
		for (int i = 0; i < contador_proyectos; i++) 
		{
			actual_proyecto = proyectos.get(i);
			for(int j=0; j<actual_proyecto.getTareas().size(); j++)
			{
				actual_tarea = actual_proyecto.getTareas().get(j);
				if(actual_tarea.getId()==id)
				{
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
	
	public tarea getTarea(int id)
	{
		proyecto actual_proyecto;
		tarea actual_tarea;
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
	
	public proyecto getProyecto(int id)
	{
		proyecto actual_proyecto;
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
		proyecto actual_proyecto;
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
}