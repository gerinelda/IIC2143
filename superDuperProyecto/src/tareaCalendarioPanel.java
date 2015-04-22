import javax.swing.*;
import java.awt.*;

public class tareaCalendarioPanel extends JPanel {

    private JButton nombre;
    private JLabel horafinal;
    private JCheckBox checkbox;
    private JLabel estado;

    public tareaCalendarioPanel(tarea tarea) {
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        nombre = new JButton(tarea.getNombre());
        horafinal = new JLabel(tarea.getHf().toString());
        estado = new JLabel(tarea.getEstado().toString());
        checkbox = new JCheckBox();
        nombre.setBorder(BorderFactory.createEtchedBorder());
        horafinal.setBorder(BorderFactory.createEtchedBorder());
        estado.setBorder(BorderFactory.createEtchedBorder());
        add(nombre);
        add(horafinal);
        add(estado);
        add(checkbox);
        setBorder(BorderFactory.createEtchedBorder());

        setVisible(true);
    }

}
