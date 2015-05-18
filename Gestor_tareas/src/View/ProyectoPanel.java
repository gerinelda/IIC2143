package View;

import Model.Proyecto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProyectoPanel extends JPanel {

    private Proyecto proyecto;

    ProyectoPanel(Proyecto proyecto) {
        this.proyecto = proyecto;
        placeComponents();
    }

    public void placeComponents() {
        setBackground(new Color(20, 35, 20, 230));
        Font font = new Font("Centhury Gothic",Font.PLAIN,16);

        TransparentButton nombreProyecto = new TransparentButton(proyecto.getNombre());
        add(nombreProyecto);
        nombreProyecto.setBorder(new LineBorder(Color.WHITE, 1));
        nombreProyecto.setFont(font);
        nombreProyecto.setForeground(Color.white);
        nombreProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProyectoFrame PF = new ProyectoFrame(proyecto);
                PF.setVisible(true);
            }
        });


    }
}
