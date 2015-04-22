import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class calendarioPanel extends JPanel {

    private JLabel dia;
    private List<tarea> listaTareas;

    /*
    TODO:
    * Agregar dias
    * Agregar tareas
    * Ajustar días a su posición real
    * Alto del grid segun sea necesario (depende de cada mes)
     */

    public calendarioPanel(Calendar calendario) {
        addDays(calendario);
        setLayout(new GridLayout(6, 7));
        setVisible(true);
    }

    private void addDays(Calendar calendario) {
        int diasEnMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
        int diaInicial = (c.get(Calendar.DAY_OF_WEEK)+5)%7;

        for (int i = 0; i < diaInicial; i++) {
            add(new diaPanel());
        }
        for (int i = 0; i < diasEnMes; i++) {
            add(new diaPanel(i));
        }

    }

}
