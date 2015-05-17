package Controller;
import View.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, ModificarTareaListener {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setListener(this);
        view.addModificarTareaListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void ModificarTarea(ActionEvent e, Tarea tarea,Proyecto proyecto) {
        if (e.getActionCommand().equals("eliminar")) {
            /** ELIMINAR TAREA **/
            System.out.println("eliminando tarea");
            model.eliminarTarea(tarea.getId());
        } else if (e.getActionCommand().equals("estado")) {
            /** CAMBIAR ESTADO **/
            // modificar el estado de la tarea (un loop entre los 3 estados? 1->2->3->1->2...
        } else if (e.getActionCommand().equals("agregar")) {
            /** AGREGAR TAREA NUEVA **/
            System.out.println("agregando tarea");
            model.agregarTarea(tarea, proyecto.getId());
        }
    }
}
