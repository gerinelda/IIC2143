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
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	private ArrayList<Proyecto> proyectos;
	private int dia;
	private ArrayList<JPanel> dias;
	private Calendar calendario;

	public VistaSemanal(Model model) {
		this.model = model;
		this.proyectos = model.getProyectos();
		setVisible(false);
		setSize(1230, 350);
		placeComponents();
	}

	private void placeComponents() {

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
			}
		};
		content.setLayout(new GridLayout(0, 1));
		content.setBackground(new Color(119, 2, 20, 160));
		setContentPane(Horizontal);

		JButton btn1 = new JButton("Siguiente");
		JButton btn2 = new JButton("Anterior");
		JButton btn3 = new JButton("Volver");

		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(119, 2, 20, 160));


		content.setLayout(new GridLayout(0, 7));
		sidebar.setLayout(new GridLayout(0, 1));
		sidebar.setMaximumSize(new Dimension(100, 1000));
		setContentPane(Horizontal);

		Horizontal.add(sidebar);
		sidebar.add(btn1);
		sidebar.add(btn2);
		sidebar.add(btn3);

		Horizontal.add(content);
		Horizontal.setBackground(new Color(119, 2, 20, 160));
		Font font = new Font("Centhury Gothic", Font.PLAIN, 20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Vista Semanal", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));

		dias = new ArrayList<JPanel>();

		for (int i = 0; i < 7; i++) {
			JPanel TempPanel = new JPanel();
			TempPanel.setOpaque(false);
			dias.add(TempPanel);
			dias.get(i).setLayout(new GridLayout(10, 1));
			content.add(dias.get(i));
			dias.get(i).setBackground(Color.gray);
		}

		// Cada día es un "Stack" vertical donde se van metiendo los TareaPanel

		calendario = Calendar.getInstance();
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		listaActualTareas = getProximosDias(7, dia);

		header(dias);
		metodo(listaActualTareas, dias);

		//seleccionar tareas semanales a partir de un lunes
		//ccon los botones cambio el lunes y actualizo
		//coordinar eventos


		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				calendario.add(Calendar.DAY_OF_MONTH,7);
				dia = calendario.get(Calendar.DAY_OF_MONTH);

				listaActualTareas = getProximosDias(7, dia);

				metodo(listaActualTareas, dias);
				content.updateUI();

				System.out.println("Siguiente");

			}
		});


		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calendario.add(Calendar.DAY_OF_MONTH,-7);
				dia = calendario.get(Calendar.DAY_OF_MONTH);

				listaActualTareas = getProximosDias(7, dia);

				metodo(listaActualTareas, dias);
				content.updateUI();

				System.out.println("anterior");
			}
		});

		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	public ArrayList<Tarea> getProximosDias(int m, int dia) {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (Proyecto p : model.getProyectos()) {
			for (Tarea t : p.getTareas()) {
				/** devolver solo tareas que vencen en los m proximos dias **/
				int n = t.getFf().getCalendario().get(Calendar.DAY_OF_MONTH) - dia;
				if (n >= 0 && n <= m) {
					lista.add(t);
				}
			}
		}
		return lista;
	}


    private void metodo (ArrayList<Tarea> listaActualTareas, ArrayList<JPanel> dias) {
    	for(JPanel jp:dias){
    		jp.removeAll();
    	}
    	header(dias);
		for(Tarea t:listaActualTareas){
			Calendar calendario2 = new GregorianCalendar(calendario.get(Calendar.YEAR),
					calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia){
				dias.get(0).add(new TareaPanel(t,model));	
			}
			calendario2.add(Calendar.DAY_OF_MONTH,1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(1).add(new TareaPanel(t,model));
			calendario2.add(Calendar.DAY_OF_MONTH,1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(2).add(new TareaPanel(t,model));
			calendario2.add(Calendar.DAY_OF_MONTH,1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(3).add(new TareaPanel(t,model));
			calendario2.add(Calendar.DAY_OF_MONTH, 1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(4).add(new TareaPanel(t,model));
			calendario2.add(Calendar.DAY_OF_MONTH,1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(5).add(new TareaPanel(t,model));
			calendario2.add(Calendar.DAY_OF_MONTH,1);
			dia = calendario2.get(Calendar.DAY_OF_MONTH);
			if(t.getFf().getD() == dia)
				dias.get(6).add(new TareaPanel(t,model));
		}
    }

    private void header(ArrayList<JPanel> dias){
		Calendar calendario2 = new GregorianCalendar(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
				calendario.get(Calendar.DAY_OF_MONTH));
		Font font = new Font("Centhury Gothic",Font.PLAIN,18);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		JLabel t = new JLabel("Lunes "+dia, JLabel.CENTER);
		t.setFont(font);
		dias.get(0).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Martes "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(1).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Miercoles "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(2).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Jueves "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(3).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Viernes "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(4).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Sabado "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(5).add(t);
		calendario2.add(Calendar.DAY_OF_MONTH, 1);
		dia = calendario2.get(Calendar.DAY_OF_MONTH);
		t = new JLabel("Domingo "+(dia), JLabel.CENTER);
		t.setFont(font);
		dias.get(6).add(t);

		/*
		dias.get(2).add(new JLabel("Miércoles " + (dia + 2), JLabel.CENTER));
		dias.get(3).add(new JLabel("Jueves "+(dia+3), JLabel.CENTER));
		dias.get(4).add(new JLabel("Viernes "+(dia+4), JLabel.CENTER));
		dias.get(5).add(new JLabel("Sábado "+(dia+5), JLabel.CENTER));
		dias.get(6).add(new JLabel("Domingo "+(dia+6), JLabel.CENTER));
		*/
    }

}
