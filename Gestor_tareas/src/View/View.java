package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {

    private Model model;
    private VistaResumen vistaResumen;

    public View(Model model) {
        this.model = model;
        vistaResumen = new VistaResumen(model);
        vistaResumen.setVisible(true);
    }

    public void setListener(ActionListener listener) {
        vistaResumen.setListener(listener);
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        vistaResumen.addModificarTareaListener(listener);
    }


}
