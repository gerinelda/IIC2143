import Controller.*;
import View.*;
import Model.*;
import javax.swing.*;


public class Application {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				runApp();
			}
		});

	}

	private static void runApp() {
		Model model = new Model();
		agregarDatosPrueba(model);
		View view = new View(model);
		Controller controller = new Controller(view, model); //singleton?
	}

	private static void agregarDatosPrueba(Model model) {
		Proyecto p1 = new Proyecto(model.getId_proyectos(),"miscelaneo",Estado.activo);
		model.agregarProyecto(p1);
		Tarea t1 = new Tarea(1,"i1 sistemas operativos",new Fecha(2,4,2015), new Fecha(4,4,2015), new Hora(12,0,0), new Hora(12,0,0),
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
				, 0, new Contexto("Contexto"));
		Tarea t2 = new Tarea(1,"examen calculo 9",new Fecha(2,4,2015), new Fecha(3,3,2015), new Hora(11,1,0), new Hora(12,2,10),"lim 2+2 = 4. e = mc..", 0, new Contexto("Contexto"));
		Tarea t4 = new Tarea(1,"examen ing. soft",new Fecha(2,4,2015), new Fecha(3,3,2015), new Hora(11,1,0), new Hora(12,2,10),"no estudiar, esta facil", 0, new Contexto("Contexto"));
		Tarea t3 = new Tarea(1,"terminar tesis",new Fecha(2,4,2015), new Fecha(6,4,2015), new Hora(10,39,39), new Hora(12,20,3),"Terminar la bla bla bla", 0, new Contexto("Contexto"));
		Tarea t5 = new Tarea(1,"terminar sprint2",new Fecha(19,5,2015), new Fecha(19,5,2015), new Hora(10,39,39), new Hora(12,20,3),"Terminar el Sprint 2 con todos los features originales", 0, new Contexto("Contexto"));
		Contexto contexto = new Contexto("miscelaneo");
		model.agregarContexto(contexto);
		t3.setEstado(Estado.terminado);
		t2.setEstado(Estado.pausado);

		model.agregarTarea(t1, p1.getId());
		model.agregarTarea(t2, p1.getId());
		model.agregarTarea(t3, p1.getId());
		model.agregarTarea(t4, p1.getId());
		model.agregarTarea(t5, p1.getId());



	}
}
