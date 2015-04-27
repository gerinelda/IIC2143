package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class View {

    private Model model;

    public View(Model model) {
        this.model = model;
        //VistaResumen vistaResumen = new VistaResumen(model);
        //vistaResumen.setVisible(true);
        CalendarioFrame vistaCalendario = new CalendarioFrame(model);
        vistaCalendario.setVisible(true);


    }

}
