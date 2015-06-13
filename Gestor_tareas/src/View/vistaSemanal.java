package View;
import Model.*;

import javax.swing.*;

/** vista que muestra tareas segun semana */
/** es un JFrame (una ventana) */
public class VistaSemanal extends JFrame {

    private Model model;

    public VistaSemanal(Model model) {
        this.model = model;
        setVisible(false);
    }


}
