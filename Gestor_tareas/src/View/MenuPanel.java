package View;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.text.DateFormatSymbols;

public class MenuPanel extends JMenuBar {

    private TransparentButton bVolver;
    private TransparentButton bSiguiente;
    private TransparentButton bAnterior;
    private JLabel fechaLabel;

    public MenuPanel(Calendar calendario) {
        setBackground(new Color(10, 19, 13, 230));
        setLayout(new FlowLayout());
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
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

        //setBackground(Color.getHSBColor(0.191F,0.3F,0.21F));
        /*
        TODO: agregar botones:
        * cambio de mes
        * volver a otra vista
        * agregar Model.Tarea (posiblemente agregar un boton a cada dia? aunque
                         me parece que esta forma es mejor para no llenar de botones)
         */
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

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
