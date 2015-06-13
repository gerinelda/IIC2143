package Controller;
import View.*;
import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class Controller implements ActionListener, ControllerListener, EmailListener {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setListener(this);
        view.addControllerListener(this);
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
            /** verificar tipo de cambio de estado
             * si es una tarea normal,
             *         v----<----<-----<-----<--------
             *      activa -> pausada -> terminada ->^
             * si la tarea esta expirada (fecha limite ya pasó)
             *      expirada <-> terminada
             */
            Calendar calendarioActual = Calendar.getInstance();
            Calendar calendarioTarea = tarea.getFf().getCalendario();
            if (calendarioActual.compareTo(calendarioTarea) > 0) {
                /** tarea expirada! */
                model.siguienteEstado2(tarea.getId());
            }
            else {
                /** tarea normal! */
                model.siguienteEstado(tarea.getId());
            }
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
            /** APLAZAR TAREA CON ID DE TAREA, ID DE PROYECTO == DIAS */
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
