package View;
import Model.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TareaFrame extends JFrame{

	JPanel content;
	JLabel id, nombre, fi, ff, hi, hf, descripcion, contexto;

    public TareaFrame(Tarea tarea) {
		tarea = new Tarea(tarea.getId(),tarea.getNombre(),tarea.getFi(),tarea.getFf(),tarea.getHi()
					,tarea.getHf(),tarea.getDescripcion(),tarea.getColor(),tarea.getContexto());
		id = new JLabel("Id: "+Integer.toString(tarea.getId()));
		nombre = new JLabel("Nombre: "+tarea.getNombre());
		fi = new JLabel("Fecha inicio: "+tarea.getFi().toString());
		hi = new JLabel("Hora inicio: "+tarea.getHi().toString());
		ff = new JLabel("Fecha final: "+tarea.getFf().toString());
		hf = new JLabel("Hora final: "+tarea.getHf().toString());
		descripcion = new JLabel("Descripcion: "+tarea.getDescripcion());
		descripcion.setBorder(BorderFactory.createEtchedBorder());
		contexto = new JLabel("Contexto: "+tarea.getContexto().getNombre());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		content = new JPanel();
		Font font = new Font("Centhury Gothic",Font.PLAIN,20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE,2),"Detalle Tarea", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,font, Color.WHITE));
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

    }




}
