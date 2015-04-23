import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreadorTareas /*extends JFrame*/ {

	
		JFrame frame;
		JTextField nombreText;
		JTextField fiText;
		JTextField ffText;
		JTextField hiText;
		JTextField hfText;
		JTextField contextoText;
		JTextField proyectoText;
		JTextField descripcionText;
	
		Gestor g;
		
		public CreadorTareas(Gestor g){
		initUI();
		this.g = g;
		}
		
		private void initUI(){
			
			frame = new JFrame("Crear Tarea");
			frame.setSize(300, 400);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel panel = new JPanel();
			frame.add(panel);
			placeComponents(panel);

			frame.setVisible(true);
						
		}
		

		private void placeComponents(JPanel panel) {

			panel.setLayout(null);

			JLabel nombreLabel = new JLabel("Nombre");
			nombreLabel.setBounds(10, 10, 80, 25);
			panel.add(nombreLabel);

			nombreText = new JTextField(20);
			nombreText.setBounds(100, 10, 160, 25);
			nombreText.setText("");
			panel.add(nombreText);
			

			JLabel fiLabel = new JLabel("Fecha inicio");
			fiLabel.setBounds(10, 40, 80, 25);
			panel.add(fiLabel);

			fiText = new JTextField(20);
			fiText.setBounds(100, 40, 160, 25);
			panel.add(fiText);

			
			JLabel ffLabel = new JLabel("Fecha fin");
			ffLabel.setBounds(10, 70, 80, 25);
			panel.add(ffLabel);
			
			ffText = new JTextField(20);
			ffText.setBounds(100, 70, 160, 25);
			panel.add(ffText);
			
			JLabel hiLabel = new JLabel("Hora inicio");
			hiLabel.setBounds(10, 100, 80, 25);
			panel.add(hiLabel);
			
			hiText = new JTextField(20);
			hiText.setBounds(100, 100, 160, 25);
			panel.add(hiText);
			
			JLabel hfLabel = new JLabel("Hora fin");
			hfLabel.setBounds(10, 130, 80, 25);
			panel.add(hfLabel);
			
			hfText = new JTextField(20);
			hfText.setBounds(100, 130, 160, 25);
			panel.add(hfText);
			
			JLabel contextoLabel = new JLabel("Contexto");
			contextoLabel.setBounds(10, 160, 80, 25);
			panel.add(contextoLabel);
			
			contextoText = new JTextField(20);
			contextoText.setBounds(100, 160, 160, 25);
			panel.add(contextoText);
			
			JLabel proyectoLabel = new JLabel("Proyecto");
			proyectoLabel.setBounds(10, 190, 80, 25);
			panel.add(proyectoLabel);
			
			proyectoText = new JTextField(20);
			proyectoText.setBounds(100, 190, 160, 25);
			panel.add(proyectoText);
			
			JLabel descripcionLabel = new JLabel("Descripci�n");
			descripcionLabel.setBounds(10, 220, 80, 25);
			panel.add(descripcionLabel);
			
			descripcionText = new JTextField(20);
			descripcionText.setBounds(100, 220, 160, 50);
			panel.add(descripcionText);
			
			//Botones
			JButton cancelarButton = new JButton("Cancelar");
			cancelarButton.setBounds(10, 290, 100, 25);
			panel.add(cancelarButton);
			
			JButton crearButton = new JButton("Crear");
			crearButton.setBounds(170, 290, 100, 25);
			panel.add(crearButton);
			
			crearButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

				tarea t = new tarea(g.getId_tareas(),nombreText.getText(),new fecha(),new fecha(), new hora(), new hora(), descripcionText.getText(),0, new contexto(contextoText.getText())); 
					g.agregarTarea(t, Integer.parseInt(proyectoText.getText()));
					frame.setVisible(false);
					
					
					System.out.println(g.getId_tareas());
					System.out.println(nombreText.getText());
					
				}
			});
			
			
			
			cancelarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.setVisible(false);
					
				}
			});
					
			
		
		}


		
}