package View;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/** ventana que muestra el calendario **/
public class CalendarioFrame extends JFrame implements ActionListener, ControllerListener {

    private Calendar calendario = Calendar.getInstance();
    private JPanel container;
    private MenuPanel menu;
    private CalendarioPanel content;
    private Model model;
    private ArrayList<ControllerListener> controllerListeners;

    public CalendarioFrame(Model model) {
        super("Calendario");

        this.model = model;

        controllerListeners = new ArrayList<>();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        calendario.setTime(new Date());

        content = new CalendarioPanel(calendario, model, this);

        menu = new MenuPanel(calendario);
        menu.setListener(this);
        
        add(container);
        setContentPane(content);
        setJMenuBar(menu);
        pack();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    /** setea los listener que escuchan los botones que no modifican el modelo */
    public void setListener(ActionListener listener) {
        menu.setListener(listener);
        content.setListener(listener);
    }

    /** controllerListener son los observadores que observan
     *  un cambio en el modelo, (el controlador principalmente)
     */
    public void addControllerListener(ControllerListener listener) {
        controllerListeners.add(listener);
        setAllListeners();
    }

    /** actualiza el calendario ante cambios de mes, etc */
    private void updateCalendario(Calendar calendario) {
        content.update(calendario);
        menu.updateFecha(calendario);
        setAllListeners();
    }

    /** setea los listener (que modifican tareas) */
    private void setAllListeners() {
        if (controllerListeners !=null) {
            for (ControllerListener listener : controllerListeners) {
                content.addControllerListener(listener);
            }
            content.addControllerListener(this);
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
            setVisible(false);
        }
    }

    private void cambiarMes(int n) {
        calendario.add(Calendar.MONTH, n);
    }

    @Override
    public void ModificarTarea(ActionEvent e, Tarea tarea, Proyecto proyecto) {
        updateCalendario(calendario);
    }

    public void updateUI() {
        content.update(calendario);
    }
}

