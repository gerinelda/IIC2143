package View;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CopyOnWriteArrayList;


/** ventana que se abre al crear una tarea rapida
 *
 */
public class TareaRapidaFrame extends JFrame implements ActionListener {
    /** abrir nueva ventana **/
    JPanel content;
    JTextField textField;
    CopyOnWriteArrayList<ControllerListener> controllerListeners;
    JComboBox<Proyecto> listaProyectos;
    JComboBox<Contexto> listaContextos;
    Model model;
    Calendar calendario;


    public TareaRapidaFrame(Model model, Calendar calendario) {
        this.model = model;
        this.calendario = calendario;
        content = new JPanel();
        content.setOpaque(false);
        add(content);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        controllerListeners = new CopyOnWriteArrayList<>();
        /** agregar botones y textos **/
        JLabel titulo = new JLabel("Agregar Tarea");
        int fontSize = 18;
        titulo.setFont(new Font(titulo.getFont().getName(), titulo.getFont().getStyle(), fontSize));
        content.add(titulo);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel XLayout1 = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        textField = new JTextField(16);
        XLayout1.add(nombre);
        XLayout1.add(textField);

        /** proyecto **/
        JPanel XLayout2 = new JPanel();
        JLabel proyecto = new JLabel("Proyecto: ");
        listaProyectos = new JComboBox<>();
        ArrayList<Proyecto> arrayProyectos = new ArrayList<>();
        if (this.model.getProyectos() != null) {
            arrayProyectos = this.model.getProyectos();
        }
        for (Proyecto p : arrayProyectos) {
            listaProyectos.addItem(p);
        }
        XLayout2.add(proyecto);
        XLayout2.add(listaProyectos);

        /** contexto **/
        JPanel XLayout25 = new JPanel();
        JLabel contexto = new JLabel("Contexto: ");
        listaContextos = new JComboBox<>();
        ArrayList<Contexto> arrayContextos = new ArrayList<>();
        if (this.model.getContextos() != null) {
            arrayContextos = model.getContextos();
        }
        for (Contexto c : arrayContextos) {
            listaContextos.addItem(c);
        }
        XLayout25.add(contexto);
        XLayout25.add(listaContextos);



        JPanel XLayout3 = new JPanel();
        XLayout3.add(btnCancelar);
        XLayout3.add(btnAceptar);

        content.add(XLayout1);
        content.add(XLayout2);
        content.add(XLayout25);
        content.add(XLayout3);

        setSize(300, 200);
        setVisible(true);

        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

    }

    public void setListeners(ArrayList<ControllerListener> listeners) {
        controllerListeners = new CopyOnWriteArrayList<>();
        for (ControllerListener listener : listeners) {
            controllerListeners.add(listener);

        }
    }

    public void addControllerListener(ControllerListener listener) {
        controllerListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar")) {
            if (!textField.getText().isEmpty()) {
                for (ControllerListener listener : controllerListeners) {
                    ActionEvent AE = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "agregar");
                    String nombre = textField.getText();
                    int id = 0; //??????
                    Tarea tareaNueva = new Tarea(
                            id,
                            nombre,
                            new Fecha(),
                            new Fecha(
                                    calendario.get(Calendar.DAY_OF_MONTH),
                                    calendario.get(Calendar.MONTH)+1,
                                    calendario.get(Calendar.YEAR)
                            ),
                            new Hora(),
                            new Hora(),
                            "",
                            model.getContextos().get(listaContextos.getSelectedIndex())
                    );
                    listener.ModificarTarea(AE, tareaNueva, model.getProyecto(((Proyecto) listaProyectos.getSelectedItem()).getId()));
                }
                setVisible(false);
            }

        }
        else if (e.getActionCommand().equals("Cancelar")) {
            textField.setText("");
            setVisible(false);
        }
    }
}
