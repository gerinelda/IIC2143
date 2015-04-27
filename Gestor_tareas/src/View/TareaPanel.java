package View;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TareaPanel extends JPanel implements ActionListener {

    private TransparentButton nombre;
    private JLabel horafinal;
    private TransparentButton delete;
    private TransparentButton estado;
    private Tarea tarea;
    private Model model;

    public TareaPanel(Tarea tarea, Model model) {
        this.model = model;
        this.tarea = tarea;
        //setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        setAlignmentX(Component.LEFT_ALIGNMENT);
        estado = new TransparentButton(tarea.getEstado());
        estado.setBorder(new LineBorder(Color.BLACK,3));
        nombre = new TransparentButton(" "+tarea.getNombre());
        delete = new TransparentButton("X ");

        add(delete);
        add(estado);
        add(nombre);

        setVisible(true);
        setOpaque(false);
        nombre.addActionListener(this);
        nombre.setActionCommand("detalle");
        delete.addActionListener(this);
        delete.setActionCommand("eliminar");

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("detalle")) {
            TareaFrame tF = new TareaFrame(tarea);
            tF.setVisible(true);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            System.out.println("eliminando Model.Tarea.... (estara listo el proximo sprint!)");
            //g.eliminarTarea(t.getId());
        }


    }

    public void setDeleteActionListener(ActionListener listener) {
        delete.setActionCommand("eliminar");
        delete.addActionListener(listener);
    }

    public Tarea getTarea() {
        return tarea;
    }

}
