package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.text.DateFormatSymbols;

public class MenuPanel extends JPanel {

    private JButton bVolver;
    private JButton bSiguiente;
    private JButton bAnterior;
    private JLabel fechaLabel;

    public MenuPanel(Calendar calendario) {
        setLayout(new FlowLayout());
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));

        bAnterior = new JButton("Mes anterior");
        add(bAnterior);
        bAnterior.setActionCommand("anterior");
        fechaLabel = new JLabel(getFecha(calendario));
        add(fechaLabel);
        bSiguiente = new JButton("Siguiente mes");
        add(bSiguiente);
        bSiguiente.setActionCommand("siguiente");

        /*
        TODO: agregar botones:
        * cambio de mes
        * volver a otra vista
        * agregar Tarea (posiblemente agregar un boton a cada dia? aunque
                         me parece que esta forma es mejor para no llenar de botones)
         */
    }

    public void setListener(ActionListener listener) {
        bSiguiente.addActionListener(listener);
        bAnterior.addActionListener(listener);
    }

    private String getFecha(Calendar calendario) {
        String fecha =
                Integer.toString(calendario.get(Calendar.DAY_OF_MONTH))
                + " de "
                +  new DateFormatSymbols().getMonths()[calendario.get(Calendar.MONTH)]
                + " del "
                + Integer.toString(calendario.get(Calendar.YEAR));
        return fecha;
    }

    public void updateFecha(Calendar calendario) {
        fechaLabel.setText(getFecha(calendario));
    }

}
