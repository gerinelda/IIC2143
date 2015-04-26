
public class Application {

	public static void main(String[] args) {
		Gestor g = new Gestor();
		///////////////// ejemplos incorporados
		// proyecto
		proyecto p1 = new proyecto(g.getId_proyectos());
		g.agregarProyecto(p1);
		// tareas
		tarea t1 = new tarea(1,"i1 sistemas operativos",new fecha(2,4,2015), new fecha(4,4,2015), new hora(12,0,0), new hora(12,0,0),"estudiar para la interrogacion 1 de sistemas operativos! no olvidar!", 0, new contexto("contexto"));
		tarea t2 = new tarea(1,"examen calculo 9",new fecha(2,4,2015), new fecha(3,3,2015), new hora(11,1,0), new hora(12,2,10),"lim 2+2 = 4. e = mc..", 0, new contexto("contexto"));
		tarea t4 = new tarea(1,"examen ing. soft",new fecha(2,4,2015), new fecha(3,3,2015), new hora(11,1,0), new hora(12,2,10),"no estudiar, esta facil", 0, new contexto("contexto"));
		tarea t3 = new tarea(1,"terminar tesis",new fecha(2,4,2015), new fecha(6,4,2015), new hora(10,39,39), new hora(12,20,3),"Terminar la tesis de computacion quantica", 0, new contexto("contexto"));
		g.agregarTarea(t1, p1.getId());
		g.agregarTarea(t2, p1.getId());
		g.agregarTarea(t3, p1.getId());
		g.agregarTarea(t4, p1.getId());
		//////////////////////
		Resumen res = new Resumen(g);

	}

}
