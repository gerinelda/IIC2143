package View;

import Model.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VistaResumen extends JFrame{
	
	private Model model;
	private CalendarioFrame calendario;
	private ArrayList<Proyecto> proyectos;
	private CreadorProyectos creadorProyectos;
	private CreadorTareas creadorTareas;
	private JPanel panel;
	private ArrayList<ModificarTareaListener> modificarTareaListeners;

	public VistaResumen(Model model) {
		this.model = model;
		this.proyectos = model.getProyectos();
		modificarTareaListeners = new ArrayList<>();
		calendario = new CalendarioFrame(model);
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		//placeComponents(panel);
		add(panel);
	}
/*
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		JLabel nombreLabel = new JLabel("Nombre | Fecha inicio | Fecha Fin | Estado");
		nombreLabel.setBounds(140, 10, 250, 25);
		panel.add(nombreLabel);
		
		for(int i = 0; i< model.getContador_proyectos(); i++){
			
			for(int j = 0 ; j < proyectos.get(i).getTareas().size();j++){
				Tarea t = proyectos.get(i).getTareas().get(j);
				
				JLabel auxLabel = new JLabel(t.getNombre() + " | " + t.getFi().toString() + " | " + t.getFf().toString() + " | " + t.getEstado().toString());
				auxLabel.setBounds(140, 40 + j*30, 450, 25);
				panel.add(auxLabel);
			}
		}

		JButton crearTareaButton = new JButton("Crear T");
		crearTareaButton.setBounds(10, 10, 100, 25);
		panel.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("Crear P");
		crearProyectoButton.setBounds(10, 40, 100, 25);
		panel.add(crearProyectoButton);
		
		JButton vistaCalendarioButton = new JButton("Calendario");
		vistaCalendarioButton.setBounds(10, 70, 100, 25);
		panel.add(vistaCalendarioButton);

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

	*/
	public void setListener(ActionListener listener) {
		calendario.setListener(listener);
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
