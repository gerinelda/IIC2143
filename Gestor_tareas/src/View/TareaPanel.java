package View;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TareaPanel extends JPanel implements ActionListener {

    private TransparentButton nombre;
    private TransparentButton delete;
    private TransparentButton estado;
    private Tarea tarea;
    private ArrayList<ControllerListener> controllerListeners;

    public TareaPanel(Tarea tarea) {
        this.tarea = tarea;
        controllerListeners = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        estado = new TransparentButton(tarea.getEstado());
        estado.setBorder(new LineBorder(Color.white, 1));
        nombre = new TransparentButton(" "+tarea.getNombre());
        delete = new TransparentButton(" X ");
        add(delete);
        add(estado);
        add(nombre);
        setVisible(true);
        setOpaque(false);
        nombre.addActionListener(this);
        nombre.setActionCommand("detalle");
        delete.setActionCommand("eliminar");
        estado.setActionCommand("estado");
        estado.addActionListener(this);
        delete.addActionListener(this);
    }

    public void mostrarTodo() {
        Font font = nombre.getFont();
        setOpaque(false);
        //setBackground(new Color(20, 35, 20, 230));

        /** DIA VENCIMIENTO **/
        JLabel fechafinal = new JLabel();
        fechafinal.setText(" "+tarea.getFf().toString()+" ");
        fechafinal.setBorder(new LineBorder(Color.WHITE, 1));
        fechafinal.setFont(font);
        fechafinal.setForeground(Color.WHITE);

        remove(nombre);
        add(fechafinal);
        add(nombre);

        /** DESCRIPCION **/
        JLabel descripcion = new JLabel();
        //add(descripcion);
        descripcion.setText(tarea.getDescripcion());
        descripcion.setBorder(new LineBorder(Color.WHITE, 1));
        descripcion.setFont(font);
        descripcion.setForeground(Color.WHITE);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("detalle")) {
            TareaFrame tF = new TareaFrame(tarea);
            if (controllerListeners!=null) {
                for (ControllerListener listener : controllerListeners) {
                    tF.addControllerListener(listener);
                }
            }
            tF.setVisible(true);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            /** si la lista de controllerListenes es no vacia */
            if (controllerListeners != null) {
                /** notificar a cada controllerListener */
                for (ControllerListener listener : controllerListeners) {
                    listener.ModificarTarea(e, tarea, new Proyecto(-1));
                }
            }
        }
        else if (e.getActionCommand().equals("estado")) {
             if (controllerListeners != null) {
                 //Collections.reverse(controllerListeners);
                for (ControllerListener listener : controllerListeners) {
                    listener.ModificarTarea(e, tarea, new Proyecto(-1));
                }
                estado.setBackground(estado.getColor(tarea.getEstado()));
            }
        }
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void addControllerListener(ControllerListener listener) {
        /** si esta anteriormente salir antes */
        for (ControllerListener listenerInList : controllerListeners) {
            if (listener.equals(listenerInList)) {
                return;
            }
        }
        controllerListeners.add(listener);
    }

    public void addListener(ActionListener listener) {
        estado.addActionListener(listener);
    }

}
