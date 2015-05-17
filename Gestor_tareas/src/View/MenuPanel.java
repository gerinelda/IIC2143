package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.text.DateFormatSymbols;

public class MenuPanel extends JMenuBar {

    private TransparentButton bVolver;
    private TransparentButton bActual;
    private TransparentButton bSiguiente;
    private TransparentButton bAnterior;
    private JLabel fechaLabel;

    public MenuPanel(Calendar calendario) {
        setBackground(new Color(10, 19, 13, 230));
        setLayout(new FlowLayout());
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));

        bVolver = new TransparentButton("Volver");
        bActual = new TransparentButton("Dia Actual");
        bVolver.setActionCommand("volver");
        bActual.setActionCommand("actual");
        add(bVolver);
        add(bActual);
        bAnterior = new TransparentButton(" Mes anterior ");
        add(bAnterior);
        bAnterior.setActionCommand("anterior");
        fechaLabel = new JLabel(getFecha(calendario));
        fechaLabel.setForeground(Color.WHITE);
        Font font = new Font("Centhury Gothic",Font.PLAIN,20);
        fechaLabel.setFont(font);
        add(fechaLabel);
        bSiguiente = new TransparentButton(" Siguiente mes ");
        add(bSiguiente);
        bSiguiente.setActionCommand("siguiente");

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

    }

    public void setListener(ActionListener listener) {
        bSiguiente.addActionListener(listener);
        bAnterior.addActionListener(listener);
        bVolver.addActionListener(listener);
        bActual.addActionListener(listener);
    }

    private String getFecha(Calendar calendario) {
        String fecha = "";
        if (
                        calendario.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                        && calendario.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                ) {
            fecha += Integer.toString(calendario.get(Calendar.DAY_OF_MONTH)) + " de ";
        }
        fecha +=
                new DateFormatSymbols().getMonths()[calendario.get(Calendar.MONTH)]
                        + " del "
                        + Integer.toString(calendario.get(Calendar.YEAR));
        return fecha;
    }

    public void updateFecha(Calendar calendario) {
        fechaLabel.setText(getFecha(calendario));
    }

}
