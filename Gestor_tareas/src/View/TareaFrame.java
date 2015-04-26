package View;

import javax.swing.*;

public class TareaFrame extends JFrame{

	Tarea t;
	JPanel content;
	JLabel id, nombre, fi, ff, hi, hf, descripcion, contexto;

    public TareaFrame(Tarea tarea) {
		t = new Tarea(tarea.getId(),tarea.getNombre(),tarea.getFi(),tarea.getFf(),tarea.getHi()
					,tarea.getHf(),tarea.getDescripcion(),tarea.getColor(),tarea.getContexto());
		id = new JLabel("Id: "+Integer.toString(t.getId()));
		nombre = new JLabel("Nombre: "+t.getNombre());
		fi = new JLabel("Fecha inicio: "+t.getFi().toString());
		hi = new JLabel("Hora inicio: "+t.getHi().toString());
		ff = new JLabel("Fecha final: "+t.getFf().toString());
		hf = new JLabel("Hora final: "+t.getHf().toString());
		descripcion = new JLabel("Descripcion: "+t.getDescripcion());
		descripcion.setBorder(BorderFactory.createEtchedBorder());
		contexto = new JLabel("Contexto: "+t.getContexto().getNombre());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		content = new JPanel();
		content.setBorder(BorderFactory.createTitledBorder("Detalle Tarea"));
		content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
		setSize(300,300);
		content.add(id);
		content.add(nombre);
		content.add(fi);
		content.add(hi);
		content.add(ff);
		content.add(hf);
		content.add(descripcion);
		content.add(contexto);
		add(content);

    }




}
