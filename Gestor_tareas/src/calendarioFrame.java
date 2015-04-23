

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class calendarioFrame extends JFrame implements ActionListener {

    // Creamos el calendario instanciado en hoy. Con los botones del menu sera instanciado en otro mes.
    Calendar calendario = Calendar.getInstance();
    JPanel container;
    menuPanel menu;
    calendarioPanel content;
    Gestor g;


    public calendarioFrame(Gestor g) {
        super("Calendario");

        this.g = g;
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        calendario.setTime(new Date());
        menu = new menuPanel(calendario);
        content = new calendarioPanel(calendario, g, this);
        add(container);
        menu.setListener(this);
        container.add(menu);
        container.add(content);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void updateCalendario(Calendar calendario) {
        content.update(calendario);
        menu.updateFecha(calendario);

    }

    // escuchar botones bSiguiente y bAnterior y cambiar mes(o a�o) del calendario
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("siguiente")) {
            siguienteMes();
            updateCalendario(calendario);
        }
        else if (e.getActionCommand().equals("anterior")) {
            anteriorMes();
            updateCalendario(calendario);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            System.out.println("eliminando tarea");
            tareaPanel tP = (tareaPanel) e.getSource();
            tarea t = tP.getTarea();
            System.out.println(t.getDescripcion());
        }
    }

    private void siguienteMes() {
        calendario.add(Calendar.MONTH, 1);
    }

    private void anteriorMes() {
        calendario.add(Calendar.MONTH, -1);
    }
}


/*
    ||||||||calendarioFrame|||||||||
    || ||||||||||menuPanel||||||| ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||                            ||
    || ||||||calendarioPanel||||| ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||||||||||||||||||||||||||||||||
 */
