package View;

import Model.Proyecto;
import Model.Tarea;

import java.awt.event.ActionEvent;

public interface ControllerListener {

    void ModificarTarea(ActionEvent e, Tarea tarea, Proyecto proyecto);

}

