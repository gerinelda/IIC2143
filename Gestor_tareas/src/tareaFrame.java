import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class tareaFrame extends JFrame{

	tarea t;
	JPanel content;
	JLabel id, nombre, fi, ff, hi, hf, descripcion, contexto;

    public tareaFrame(tarea tarea) {
		t = new tarea(tarea.getId(),tarea.getNombre(),tarea.getFi(),tarea.getFf(),tarea.getHi()
					,tarea.getHf(),tarea.getDescripcion(),tarea.getColor(),tarea.getContexto());
		id = new JLabel("Id: "+Integer.toString(t.getId()));
		nombre = new JLabel("Nombre: "+t.getNombre());
		fi = new JLabel("fecha inicio: "+t.getFi().toString());
		hi = new JLabel("hora inicio: "+t.getHi().toString());
		ff = new JLabel("fecha final: "+t.getFf().toString());
		hf = new JLabel("hora final: "+t.getHf().toString());
		descripcion = new JLabel("Descripcion: "+t.getDescripcion());
		descripcion.setBorder(BorderFactory.createEtchedBorder());
		contexto = new JLabel("contexto: "+t.getContexto().getNombre());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		content = new JPanel();
		content.setBorder(BorderFactory.createTitledBorder("Detalle tarea"));
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
