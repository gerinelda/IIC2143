import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class diaPanel extends JPanel {

    int mes;
    int dia;
    int year;
    ArrayList<tarea> tareas;
    Gestor g;

    public diaPanel(int dia, int mes, int year, Gestor g) {
        this.dia = dia;
        this.mes = mes;
        this.year = year;
        setPreferredSize(new Dimension(220, 140));
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder(new LineBorder(Color.black), Integer.toString(dia)),
                new EmptyBorder(1, 1, 1, 1)
        ));
        if (dia == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
            setBackground(Color.lightGray);
        }
    }

    /**
     * empty panel *
     */
    public diaPanel() {
        setPreferredSize(new Dimension(220,140));
        setVisible(true);
    }

    /**
     * agrega tareas al dia *
     */
    public void addTarea(tarea tarea) {
        tareaPanel tP = new tareaPanel(tarea, g);
        add(tP);

    }

    public boolean mismaFecha(fecha fecha) {
        //mes (de Calendar.MONTH) parte en 0 hasta 11
        if (fecha.getD() == dia && fecha.getM() == mes+1 && fecha.getY() == year) {
            return true;
        } else return false;
    }


}

