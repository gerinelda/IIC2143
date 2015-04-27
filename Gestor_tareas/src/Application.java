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
		Controller controller = new Controller(view, model);
	}

	private static void agregarDatosPrueba(Model model) {
		Proyecto p1 = new Proyecto(model.getId_proyectos());
		model.agregarProyecto(p1);
		Tarea t1 = new Tarea(1,"i1 sistemas operativos",new Fecha(2,4,2015), new Fecha(4,4,2015), new Hora(12,0,0), new Hora(12,0,0),"estudiar para la interrogacion 1 de sistemas operativos! no olvidar!", 0, new Contexto("Model.Contexto"));
		Tarea t2 = new Tarea(1,"examen calculo 9",new Fecha(2,4,2015), new Fecha(3,3,2015), new Hora(11,1,0), new Hora(12,2,10),"lim 2+2 = 4. e = mc..", 0, new Contexto("Model.Contexto"));
		Tarea t4 = new Tarea(1,"examen ing. soft",new Fecha(2,4,2015), new Fecha(3,3,2015), new Hora(11,1,0), new Hora(12,2,10),"no estudiar, esta facil", 0, new Contexto("Model.Contexto"));
		Tarea t3 = new Tarea(1,"terminar tesis",new Fecha(2,4,2015), new Fecha(6,4,2015), new Hora(10,39,39), new Hora(12,20,3),"Terminar la tesis de computacion quantica", 0, new Contexto("Model.Contexto"));
		t3.setEstado(Estado.terminado);
		t2.setEstado(Estado.pausado);

		model.agregarTarea(t1, p1.getId());
		model.agregarTarea(t2, p1.getId());
		model.agregarTarea(t3, p1.getId());
		model.agregarTarea(t4, p1.getId());
	}
}
