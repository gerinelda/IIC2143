package View;

import Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** cuadrado con el nombre del proyecto (y el boton) */
public class ProyectoPanel extends JPanel {

    private Proyecto proyecto;
    private Model model;
    private JButton btnEliminar;


    ProyectoPanel(Proyecto proyecto, Model model) {
        this.model = model;
        this.proyecto = proyecto;
        placeComponents();
    }

    public void placeComponents() {
        //setBackground(new Color(20, 35, 20, 230));
        setOpaque(false);
        Font font = new Font("Centhury Gothic",Font.PLAIN,16);

        TransparentButton nombreProyecto = new TransparentButton(proyecto.getNombre());
        add(nombreProyecto);
        nombreProyecto.setBorder(new LineBorder(Color.WHITE, 1));
        nombreProyecto.setFont(font);
        nombreProyecto.setForeground(Color.white);
        nombreProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProyectoFrame PF = new ProyectoFrame(proyecto, model);
                PF.setVisible(true);
            }
        });
        if (!proyecto.getNombre().equals("miscelaneo")) {
            btnEliminar = new JButton("Eliminar");
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("eliminado proyecto " + proyecto.getNombre());
                    model.eliminarProyecto(proyecto.getId());
                    btnEliminar.setVisible(false);
                    nombreProyecto.setVisible(false);
                    updateUI();
                }
            });
            add(btnEliminar);
        }


    }
}
