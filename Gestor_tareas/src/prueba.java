import javax.swing.*;

public class prueba {

	public static void main(String[] args) {
		Gestor g = new Gestor();
		/////////////////
		proyecto p1 = new proyecto(g.getId_proyectos());
		g.agregarProyecto(p1);
		tarea t1 = new tarea(1,"super tarea",new fecha(2,4,2015), new fecha(4,4,2015), new hora(12,0,0), new hora(12,0,0),"hola", 0, new contexto("contexto"));
		g.agregarTarea(t1, p1.getId());
		//////////////////////
//		tareaFrame tareaf = new tareaFrame(t1);
		//tareaf.setVisible(true);
		Resumen res = new Resumen(g);



	}

}
