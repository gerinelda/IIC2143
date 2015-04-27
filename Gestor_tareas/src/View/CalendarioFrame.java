package View;
import Model.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CalendarioFrame extends JFrame implements ActionListener {

    private Calendar calendario = Calendar.getInstance();
    private JPanel container;
    private MenuPanel menu;
    private CalendarioPanel content;
    private Model model;


    public CalendarioFrame(Model model) {
        super("Calendario");

        this.model = model;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        calendario.setTime(new Date());

        content = new CalendarioPanel(calendario, model, this);

        menu = new MenuPanel(calendario);
        menu.setListener(this);
        
        add(container);
        //container.add(menu);
        JMenu menu2 = new JMenu();
        menu2.add(new JMenuItem("MenuItem"));
        menu2.setVisible(true);
        container.add(content);
        setJMenuBar(menu);
        pack();

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
            System.out.println("eliminando Model.Tarea");
            TareaPanel tP = (TareaPanel) e.getSource();
            Tarea t = tP.getTarea();
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
    ||||||||CalendarioFrame|||||||||
    || ||||||||||MenuPanel||||||| ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||                            ||
    || ||||||CalendarioPanel||||| ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||||||||||||||||||||||||||||||||
 */
