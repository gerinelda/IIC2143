package Controller;
import View.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, ControllerListener, EmailListener {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setListener(this);
        view.addModificarTareaListener(this);
        view.addEmailListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void ModificarTarea(ActionEvent e, Tarea tarea,Proyecto proyecto) {
        if (e.getActionCommand().equals("eliminar")) {
            /** ELIMINAR TAREA **/
            System.out.println("eliminando tarea " + tarea.getNombre());
            model.eliminarTarea(tarea.getId());
        } else if (e.getActionCommand().equals("estado")) {
            /** CAMBIAR ESTADO **/
            System.out.println("cambiando estado");
            model.siguienteEstado(tarea.getId());
        } else if (e.getActionCommand().equals("agregar")) {
            /** AGREGAR TAREA NUEVA **/
            System.out.println("agregando tarea");
            model.agregarTarea(tarea, proyecto.getId());
        } else if (e.getActionCommand().equals("agregarProyecto")) {
            /** AGREGAR NUEVO PROYECTO **/
            System.out.println("agregando proyecto nuevo");
            model.agregarProyecto(proyecto);
        } else if (e.getActionCommand().equals("agregarContexto")) {
            /** AGREGAR NUEVO CONTEXTO **/
            System.out.println("agregando contexto nuevo");
            model.agregarContexto(new Contexto(proyecto.getNombre()));
        } else if (e.getActionCommand().equals("aplazar")) {
            /** APLAZAR TAREA CON ID DE TAREA, DIAS = ID DE PROYECTO **/
            System.out.println("aplazando "+proyecto.getId()+" dias");
            model.cambiarTarea(tarea);
        }
        updateAll();
    }

    public void updateAll() {
        view.actualizarProyectos();
        view.updateAll();
    }

    @Override
    public void EnviarEmail(String contenido, String destinatario, String asunto) {
        mail mymail = new mail();
        mymail.send(contenido,destinatario,asunto);
    }
}
