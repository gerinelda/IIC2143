package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {

    private Model model;
    CalendarioFrame vistaCalendario;

    public View(Model model) {
        this.model = model;
        //VistaResumen vistaResumen = new VistaResumen(model);
        //vistaResumen.setVisible(true);
        vistaCalendario = new CalendarioFrame(model);
        vistaCalendario.setVisible(true);
    }

    public void setListener(ActionListener listener) {
        vistaCalendario.setListener(listener);
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        vistaCalendario.addModificarTareaListener(listener);
    }


}
