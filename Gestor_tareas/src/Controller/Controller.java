package Controller;
import View.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("eliminar")) {
            //model.eliminarTarea();
        }
    }
}
