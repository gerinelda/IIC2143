package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class TareaFrame extends JFrame {

	JPanel content;
	JLabel id, nombre, fi, ff, hi, hf, descripcion, contexto;
	ArrayList<ModificarTareaListener> modificarTareaListeners;
	Tarea tarea;

	public TareaFrame(Tarea tarea) {
		modificarTareaListeners = new ArrayList<>();
		this.tarea = tarea;
		placeComponents();
	}

	public void placeComponents() {
		id = new JLabel("Id: " + Integer.toString(tarea.getId()));
		nombre = new JLabel("Nombre: " + tarea.getNombre());
		fi = new JLabel("Fecha inicio: " + tarea.getFi().toString());
		hi = new JLabel("Hora inicio: " + tarea.getHi().toString());
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

		content.setBackground(new Color(47, 0, 8));

		JButton btn1 = new JButton("+1 dia");
		JButton btn2 = new JButton("+1 semana");
		JButton btn3 = new JButton("+1 mes");
		content.add(btn1);
		content.add(btn2);
		content.add(btn3);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e,1);
			}
		});
		btn1.setActionCommand("aplazar");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e,7);
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificarListeners(e,30);
			}
		});
		btn2.setActionCommand("aplazar");
		btn3.setActionCommand("aplazar");
	}

	private void notificarListeners(ActionEvent e, int dias) {
		Tarea tareaNueva = tarea;
		tareaNueva.aplazar(dias);
		if (modificarTareaListeners!=null) {
			for (ModificarTareaListener listener : modificarTareaListeners) {
				listener.ModificarTarea(e, tareaNueva, new Proyecto(dias));
			}
		}
		tarea = tareaNueva;
		ff.setText("Fecha final: " + tarea.getFf().toString());
	}

	public void addModificarTareaListener(ModificarTareaListener listener) {
		modificarTareaListeners.add(listener);
	}
}

