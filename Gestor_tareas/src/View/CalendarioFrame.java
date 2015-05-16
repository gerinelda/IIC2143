package View;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CalendarioFrame extends JFrame implements ActionListener, ModificarTareaListener {

    private Calendar calendario = Calendar.getInstance();
    private JPanel container;
    private MenuPanel menu;
    private CalendarioPanel content;
    private Model model;
    private ArrayList<ModificarTareaListener> modificarTareaListeners;

    public CalendarioFrame(Model model) {
        super("Calendario");

        this.model = model;

        modificarTareaListeners = new ArrayList<>();
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
        setContentPane(content);
        //container.add(content);
        setJMenuBar(menu);
        pack();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public void setListener(ActionListener listener) {
        menu.setListener(listener);
        content.setListener(listener);
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        modificarTareaListeners.add(listener);
        setAllListeners();
    }

    private void updateCalendario(Calendar calendario) {
        content.update(calendario);
        menu.updateFecha(calendario);
        setAllListeners();
    }

    private void setAllListeners() {
        if (modificarTareaListeners !=null) {
            for (ModificarTareaListener listener : modificarTareaListeners) {
                content.addModificarTareaListener(listener);
            }
            content.addModificarTareaListener(this);
        }
    }

    // escuchar botones bSiguiente y bAnterior y cambiar mes(o a�o) del calendario
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("siguiente")) {
            cambiarMes(1);
            updateCalendario(calendario);
        }
        else if (e.getActionCommand().equals("anterior")) {
            cambiarMes(-1);
            updateCalendario(calendario);
        } else if (e.getActionCommand().equals("actual")) {
            calendario = Calendar.getInstance();
            updateCalendario(calendario);
        } else if (e.getActionCommand().equals("volver")) {
            // vuelve a vista resumen
            // no implementado
            System.out.println("no implementado, implementar para sprint 2!!");
        }
    }

    private void cambiarMes(int n) {
        calendario.add(Calendar.MONTH, n);
    }

    @Override
    public void ModificarTarea(ActionEvent e, Tarea tarea, Proyecto proyecto) {
        updateCalendario(calendario);
    }
}

