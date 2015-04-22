import com.sun.javafx.geom.AreaOp;

import javax.swing.*;
import java.util.*;

public class calendarioFrame extends JFrame {

    // Creamos el calendario instanciado en hoy. Con los botones del menu sera instanciado en otro mes.
    Calendar calendario = Calendar.getInstance();


    public calendarioFrame() {
        super("Calendario");


        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        calendario.setTime(new Date());
        JPanel menu = new menuPanel(calendario);
        JPanel content = new calendarioPanel(calendario);
        add(container);
        container.add(menu);
        container.add(content);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // escuchar botones bSiguiente y bAnterior y cambiar mes(o año) del calendario
}


/*
    ||||||||calendarioFrame|||||||||
    || ||||||||||menuPanel||||||| ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||                            ||
    || ||||||calendarioPanel||||| ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || ||                      || ||
    || |||||||||||||||||||||||||| ||
    ||||||||||||||||||||||||||||||||
 */
