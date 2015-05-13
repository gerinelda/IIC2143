package Controller;
import View.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, EliminarTareaListener {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setListener(this);
        view.setEliminarTareaListener(this);
        //view.setDeleteTareaListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("eliminar")) {
            //int id = ((TransparentButton) e.getSource()).getId();
            //model.eliminarTarea(id);
            //System.out.println("eliminando tarea " + id);
        }
    }

    @Override
    public void EliminarTarea(int id) {
        System.out.println("eliminando tarea "+id);
        model.eliminarTarea(id);
    }
}
