import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Calendar;

public class diaPanel extends JPanel {


    public diaPanel(int dia) {
        dia++;
        setPreferredSize(new Dimension(200,140));
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                //new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.GRAY, Color.green, Color.blue),
                new TitledBorder(new LineBorder(Color.black), Integer.toString(dia)),
                //new TitledBorder("titulo"),
                //new EtchedBorder(),
                //new LineBorder(Color.black),
                new EmptyBorder(1, 1, 1, 1)
        ));
        if (dia == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
            setBackground(Color.lightGray);
        }

        //EJEMPLO TEMPORAL
        tarea tarea = new tarea(23,"entrega3",new fecha(2,3,2014),
                new fecha(3,1,2016),new hora(14,23,23),new hora(19,14,20),
                "entrega 3 del ramo X", 1,new contexto("contexto ejemplo"));

        if (dia == 13) {
            addTarea(tarea);
        }
        //

    }

    /** empty panel **/
    public diaPanel() {
        setPreferredSize(new Dimension(70, 70));
        setVisible(true);
    }

    /** agrega tareas al dia **/
    public void addTarea(tarea tarea) {
        add(new tareaCalendarioPanel(tarea));
    }
}

