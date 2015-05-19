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
    private ArrayList<ModificarTareaListener> modificarTareaListeners;

    public TareaPanel(Tarea tarea) {
        this.tarea = tarea;
        modificarTareaListeners = new ArrayList<>();
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
        setOpaque(true);
        setBackground(new Color(20, 35, 20, 230));

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
            if (modificarTareaListeners!=null) {
                for (ModificarTareaListener listener : modificarTareaListeners) {
                    tF.addModificarTareaListener(listener);
                }
            }
            tF.setVisible(true);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            if (modificarTareaListeners != null) {
                for (ModificarTareaListener listener : modificarTareaListeners) {
                    listener.ModificarTarea(e, tarea, new Proyecto(-1));
                }
            }
        }
        else if (e.getActionCommand().equals("estado")) {
             if (modificarTareaListeners != null) {
                 //Collections.reverse(modificarTareaListeners);
                for (ModificarTareaListener listener : modificarTareaListeners) {
                    listener.ModificarTarea(e, tarea, new Proyecto(-1));
                }
                estado.setBackground(estado.getColor(tarea.getEstado()));
            }
        }
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        for (ModificarTareaListener listenerInList : modificarTareaListeners) {
            if (listener.equals(listenerInList)) {
                return;
            }
        }
        modificarTareaListeners.add(listener);
    }
    public void addListener(ActionListener listener) {
        estado.addActionListener(listener);
    }

}
