
public class prueba {

	public static void main(String[] args) {
		Gestor g = new Gestor();
		proyecto p1 = new proyecto(g.getId_proyectos());
		g.agregarProyecto(p1);
		tarea t1 = new tarea(1,"super tarea",new fecha(12,02,2014), new fecha(13,02,2014), new hora(12,0,0), new hora(12,0,0),"hola", 0, new contexto("contexto"));
		g.agregarTarea(t1, p1.getId());

		System.out.println(g.exportarTarea(t1.getId(), "t1.bin"));
		tarea tn = g.importarTarea("t1.bin");
		System.out.println(tn.getNombre());
	}

}
