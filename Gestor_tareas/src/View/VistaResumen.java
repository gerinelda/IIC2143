package View;

import Model.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class VistaResumen extends JFrame{
	
	private Model model;
	private CalendarioFrame calendario;
	private ArrayList<Proyecto> proyectos;
	private CreadorProyectos creadorProyectos;
	private CreadorTareas creadorTareas;
	private ArrayList<ModificarTareaListener> modificarTareaListeners;
	private JPanel content;

	public VistaResumen(Model model) {
		this.model = model;
		this.proyectos = model.getProyectos();
		modificarTareaListeners = new ArrayList<>();
		calendario = new CalendarioFrame(model);
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents();
	}

	private void placeComponents() {
		JPanel Horizontal = new JPanel();
		Horizontal.setLayout(new BoxLayout(Horizontal, BoxLayout.X_AXIS));
		Horizontal.setBackground(new Color(35, 35, 35,230));
		content = new JPanel();
		content.setBackground(new Color(35, 35, 35, 230));

		JMenuBar menubar = new JMenuBar();
		JButton btn1 = new JButton("btn1");
		JButton btn2 = new JButton("btn2");
		JPanel sidebar = new JPanel();

		content.setLayout(new GridLayout(0,1));
		sidebar.setLayout(new GridLayout(0, 1));
		Dimension d = sidebar.getPreferredSize();
		sidebar.setPreferredSize(new Dimension(d.width+60,d.height));
		setJMenuBar(menubar);
		setContentPane(Horizontal);

		Horizontal.add(sidebar);
		Horizontal.add(content);
		menubar.add(btn1);
		menubar.add(btn2);

		/** Content **/
		content.setBorder(new LineBorder(Color.green, 4));
		JLabel nombreLabel = new JLabel("VISTA RESUMEN");
		content.add(nombreLabel);
		ArrayList<Tarea> listaTareas = getListaCompleta();
		mostrarTareas(listaTareas);


		JButton crearTareaButton = new JButton("Crear T");
		sidebar.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("Crear P");
		sidebar.add(crearProyectoButton);
		
		JButton vistaCalendarioButton = new JButton("Calendario");
		sidebar.add(vistaCalendarioButton);

		crearTareaButton.addActionListener(e -> {
            creadorTareas = new CreadorTareas(model);
			for (ModificarTareaListener listener : modificarTareaListeners) {
				creadorTareas.addModificarTareaListener(listener);
			}
			creadorTareas.setVisible(true);
		});

		crearProyectoButton.addActionListener(e -> {
			creadorProyectos = new CreadorProyectos(model);

			creadorProyectos.setVisible(true);
		});

		vistaCalendarioButton.addActionListener(e -> calendario.setVisible(true));
	}

	public void mostrarTareas(ArrayList<Tarea> tareas) {
		content.removeAll();
		for (Tarea t : tareas) {
			TareaPanel TP = new TareaPanel(t);
			TP.mostrarTodo();
			content.add(TP);

		}
	}

	public void setListener(ActionListener listener) {
		calendario.setListener(listener);
	}

	public ArrayList<Tarea> getListaCompleta() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for(int i = 0; i< model.getContador_proyectos(); i++){
			for(int j = 0 ; j < proyectos.get(i).getTareas().size();j++){
				Tarea t = proyectos.get(i).getTareas().get(j);
				lista.add(t);
			}
		}
		return lista;
	}

	public void addModificarTareaListener(ModificarTareaListener listener) {
		calendario.addModificarTareaListener(listener);
		for (ModificarTareaListener modificarTareaListener : modificarTareaListeners) {
			if (listener.equals(modificarTareaListener)) {
				return;
			}
		}
		modificarTareaListeners.add(listener);
	}


}
