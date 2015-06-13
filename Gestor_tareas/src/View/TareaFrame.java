package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Ventana que se abre al abrir una tarea
 * contiene informacion de la tarea y los botones de aplazar
 */
public class TareaFrame extends JFrame {

	JPanel content;
	JLabel id, nombre, fi, ff, hi, hf, descripcion, contexto;
	ArrayList<ControllerListener> controllerListeners;
	Tarea tarea;

	public TareaFrame(Tarea tarea) {
		controllerListeners = new ArrayList<>();
		this.tarea = tarea;
		placeComponents();
	}

	public void placeComponents() {
		id = new JLabel("Id: " + Integer.toString(tarea.getId()));
		nombre = new JLabel("Nombre: " + tarea.getNombre());
		fi = new JLabel("Fecha creacion: " + tarea.getFi().toString());
		hi = new JLabel("Hora creacion: " + tarea.getHi().toString());
		ff = new JLabel("Fecha final: " + tarea.getFf().toString());
		hf = new JLabel("Hora final: " + tarea.getHf().toString());
		descripcion = new JLabel("Descripcion: " + tarea.getDescripcion());
		descripcion.setBorder(BorderFactory.createEtchedBorder());
		contexto = new JLabel("Contexto: " + tarea.getContexto().getNombre());

		content = new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Font font = new Font("Centhury Gothic", Font.PLAIN, 20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Detalle Tarea", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		setSize(300, 300);
		content.add(id);
		content.add(nombre);
		content.add(fi);
		content.add(hi);
		content.add(ff);
		content.add(hf);
		content.add(descripcion);
		content.add(contexto);
		add(content);

		id.setForeground(Color.WHITE);
		descripcion.setForeground(Color.WHITE);
		nombre.setForeground(Color.WHITE);
		contexto.setForeground(Color.WHITE);
		hi.setForeground(Color.WHITE);
		hf.setForeground(Color.WHITE);
		ff.setForeground(Color.WHITE);
		fi.setForeground(Color.WHITE);

		Color bgColor = new Color(47,0,8);
		content.setBackground(bgColor);

		JButton btn1 = new JButton("+1 dia");
		JButton btn2 = new JButton("+1 sem");
		JButton btn3 = new JButton("+1 mes");
		JButton btn4 = new JButton("-1 dia");
		JButton btn5 = new JButton("-1 sem");
		JButton btn6 = new JButton("-1 mes");
		JPanel aplazarPanel = new JPanel();
		JPanel subPanel1 = new JPanel();
		JPanel subPanel2 = new JPanel();

		aplazarPanel.setLayout(new BoxLayout(aplazarPanel,BoxLayout.X_AXIS));
		subPanel1.setBackground(bgColor);
		subPanel2.setBackground(bgColor);
		subPanel1.setLayout(new BoxLayout(subPanel1,BoxLayout.Y_AXIS));
		subPanel2.setLayout(new BoxLayout(subPanel2,BoxLayout.Y_AXIS));
		subPanel2.add(btn1); // +1 dia
		subPanel2.add(btn2); // +1 semana
		subPanel2.add(btn3); // +1 mes
		subPanel1.add(btn4); // -1 dia
		subPanel1.add(btn5); // -1 semana
		subPanel1.add(btn6); // -1 mes
		aplazarPanel.add(subPanel1);
		aplazarPanel.add(subPanel2);
		content.add(aplazarPanel);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, 1);
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, 7);
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, 30);
			}
		});
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, -1);
			}
		});
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, -7);
			}
		});
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e, -30);
			}
		});
		btn1.setActionCommand("aplazar");
		btn2.setActionCommand("aplazar");
		btn3.setActionCommand("aplazar");
		btn4.setActionCommand("aplazar");
		btn5.setActionCommand("aplazar");
		btn6.setActionCommand("aplazar");
	}

	private void notificarListeners(ActionEvent e, int dias) {
		Tarea tareaNueva = tarea;
		tareaNueva.aplazar(dias);
		tarea.actualizarEstado();
		if (controllerListeners !=null) {
			for (ControllerListener listener : controllerListeners) {
				listener.ModificarTarea(e, tareaNueva, new Proyecto(dias));
			}
		}
		tarea = tareaNueva;
		ff.setText("Fecha final: " + tarea.getFf().toString());
	}

	public void addControllerListener(ControllerListener listener) {
		controllerListeners.add(listener);
	}
}

