package View;

import Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProyectoFrame extends JFrame {

    private Proyecto proyecto;
    private Model model;

    public ProyectoFrame(Proyecto proyecto, Model model) {
        this.model = model;
        this.proyecto = proyecto;
        setSize(200,200);
        placeComponents();
    }

    public void placeComponents() {
        JPanel content = new JPanel();
        Font font = new Font("Centhury Gothic",Font.PLAIN,20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2),proyecto.getNombre() ,TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);
        content.setBackground(new Color(47, 0, 8));
        for (Tarea tarea : proyecto.getTareas()) {
            JLabel temp = new JLabel(tarea.getNombre());
            temp.setForeground(Color.WHITE);
            add(temp);
        }
    }
}
