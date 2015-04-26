package View;

import Logic.*;
import View.CalendarioFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VistaResumen extends JFrame{
	
	private Gestor g;
	private CalendarioFrame calendario;
	private ArrayList<proyecto> proyectos;
	private JFrame frame;

	public VistaResumen(Gestor g){
		this.g = g;
		this.proyectos = g.getProyectos();
		initUI();
	}
	
	
	private void initUI(){
		
		frame = new JFrame("Vista VistaResumen.VistaResumen");
		frame.setSize(500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}
	

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel nombreLabel = new JLabel("Nombre | Logic.Fecha inicio | Logic.Fecha Fin | Logic.Estado");
		nombreLabel.setBounds(140, 10, 250, 25);
		panel.add(nombreLabel);
		
		for(int i = 0; i< g.getContador_proyectos(); i++){
			
			for(int j = 0 ; j < proyectos.get(i).getTareas().size();j++){
				Tarea t = proyectos.get(i).getTareas().get(j);
				
				JLabel auxLabel = new JLabel(t.getNombre() + " | " + t.getFi().toString() + " | " + t.getFf().toString() + " | " + t.getEstado().toString());
				auxLabel.setBounds(140, 40 + j*30, 450, 25);
				panel.add(auxLabel);
				
				
			}
		}
			
			

		
		//Botones
		JButton crearTareaButton = new JButton("Crear T");
		crearTareaButton.setBounds(10, 10, 100, 25);
		panel.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("Crear P");
		crearProyectoButton.setBounds(10, 40, 100, 25);
		panel.add(crearProyectoButton);
		
		JButton vistaCalendarioButton = new JButton("Calendario");
		vistaCalendarioButton.setBounds(10, 70, 100, 25);
		panel.add(vistaCalendarioButton);
		
	
		crearTareaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				g.ct.frame.setVisible(true);
			//	frame.setVisible(false);
				
			}
		});
		
		crearProyectoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				g.cp.frame.setVisible(true);
				//frame.setVisible(false);
				
			}
		});
		
		
		vistaCalendarioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calendario = new CalendarioFrame(g);
				calendario.setVisible(true);
			}
		});
		
		
		
		
		
		
		
	
	}



}
