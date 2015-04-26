package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TareaPanel extends JPanel implements ActionListener {

    private JButton nombre;
    private JLabel horafinal;
    private JButton delete;
    private JLabel estado;
    private Tarea t;
    private Gestor g;

    public TareaPanel(Tarea tarea, Gestor g) {
        this.g = g;
        this.t = tarea;
        //setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setLayout(new FlowLayout());

        nombre = new JButton(tarea.getNombre());
        horafinal = new JLabel(tarea.getHf().toString());
        estado = new JLabel(tarea.getEstado().toString());
        delete = new JButton("X");
        delete.setToolTipText("elimina Tarea");
        nombre.setBorder(BorderFactory.createEtchedBorder());
        horafinal.setBorder(BorderFactory.createEtchedBorder());
        estado.setBorder(BorderFactory.createEtchedBorder());
        add(nombre);
        add(horafinal);
        add(estado);
        add(delete);
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
        nombre.addActionListener(this);
        nombre.setActionCommand("detalle");
        delete.addActionListener(this);
        delete.setActionCommand("eliminar");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("detalle")) {
            TareaFrame tF = new TareaFrame(t);
            tF.setVisible(true);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            System.out.println("eliminando Tarea.... (estara listo el proximo sprint!)");
            //g.eliminarTarea(t.getId());
        }


    }

    public void setDeleteActionListener(ActionListener listener) {
        delete.setActionCommand("eliminar");
        delete.addActionListener(listener);
    }

    public Tarea getTarea() {
        return t;
    }

}
