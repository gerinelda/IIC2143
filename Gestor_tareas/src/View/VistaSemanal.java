package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/** vista que muestra tareas segun semana */
/** es un JFrame (una ventana) */

public class VistaSemanal extends JFrame {

    private Model model;
    private JPanel content;
    private Image BGimage;
    private ArrayList<Tarea> listaActualTareas;
    
    public VistaSemanal(Model model) {
        this.model = model;
        setVisible(false);
        setSize(830, 350);
        placeComponents();
    }
    
    private void placeComponents(){
    	
    	JPanel Horizontal = new JPanel();
		Horizontal.setLayout(new BoxLayout(Horizontal, BoxLayout.X_AXIS));

        try {
            BGimage = ImageIO.read(getClass().getResource("/resources/imagenes/calendarioBG.jpg"));

        } catch (Exception e) {
            setBackground(Color.getHSBColor(0.191F, 0.3F, 0.21F));
            System.out.println(e);
            System.out.println("Background image loading failed.");
        }
        
		content = new JPanel() {
			
					
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(BGimage, 0, 0, this);
			g.setColor(new Color(119, 2, 20, 160));
			g.fillRect(0, 0, getWidth(), getHeight());
		}};
        
		content.setLayout(new GridLayout(0, 1));
		setContentPane(Horizontal);

		JButton btn1 = new JButton("Siguiente");
		JButton btn2 = new JButton("Anterior");
		JButton btn3 = new JButton("Volver");
		
		JPanel sidebar = new JPanel();

		content.setLayout(new GridLayout(0, 7));
		sidebar.setLayout(new GridLayout(0, 1));
		sidebar.setMaximumSize(new Dimension(100, 1000));
		setContentPane(Horizontal);

		Horizontal.add(sidebar);
		sidebar.add(btn1);
		sidebar.add(btn2);
		sidebar.add(btn3);
		
		Horizontal.add(content);
		Font font = new Font("Centhury Gothic",Font.PLAIN,20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Vista Semanal", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));

		System.out.println("Semana");
		
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				System.out.println("Siguiente");
				
			}
		});
		
		
	//	GridLayout horario = new GridLayout(0,7);
		
		
		ArrayList<JPanel> dias = new ArrayList<JPanel>();
	
		
		for(int i = 0 ; i<7 ;i++){
			
			dias.add( new JPanel());
			dias.get(i).setLayout(new GridLayout(10,1));
			content.add(dias.get(i));
			dias.get(i).setBackground(Color.gray);
		}
		
		// Cada día es un "Stack" vertical donde se van metiendo los TareaPanel
		
		
		dias.get(0).add(new JLabel("Lunes", JLabel.CENTER));		
		dias.get(1).add(new JLabel("Martes", JLabel.CENTER));
		dias.get(2).add(new JLabel("Miércoles", JLabel.CENTER));
		dias.get(3).add(new JLabel("Jueves", JLabel.CENTER));
		dias.get(4).add(new JLabel("Viernes", JLabel.CENTER));
		dias.get(5).add(new JLabel("Sábado", JLabel.CENTER));
		dias.get(6).add(new JLabel("Domingo", JLabel.CENTER));
		
		
		Tarea t = new Tarea(1,"Examen",new Fecha(1,1,2015), new Fecha(26,6,2015), new Hora(12,0,0), new Hora(8,30),"Ingenieria de Software",new Contexto("Universidad"));
		
		Fecha lunesActual = new Fecha(15,6,2015);
		
		//seleccionar tareas semanales a partir de un lunes
		//ccon los botones cambio el lunes y actualizo
		//coordinar eventos
		
		
		dias.get(3).add(new TareaPanel(t,model));
    }


}
