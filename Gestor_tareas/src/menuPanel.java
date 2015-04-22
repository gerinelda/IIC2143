import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.text.DateFormatSymbols;

public class menuPanel extends JPanel {

    private JButton bVolver;
    private JButton bSiguiente;
    private JButton bAnterior;

    public menuPanel(Calendar calendario) {
        setLayout(new FlowLayout());
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        String fecha =
                Integer.toString(calendario.get(Calendar.DAY_OF_MONTH))
                + " de "
                +  new DateFormatSymbols().getMonths()[calendario.get(Calendar.MONTH)]
                + " del "
                + Integer.toString(calendario.get(Calendar.YEAR));
        add(new JLabel(fecha));

        /*
        TODO: agregar botones:
        * cambio de mes
        * volver a otra vista
        * agregar tarea (posiblemente agregar un boton a cada dia? aunque
                         me parece que esta forma es mejor para no llenar de botones)
         */
    }
}
