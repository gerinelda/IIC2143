package View;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.*;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** JPanel que muestra las tareas por contexto */
public class VistaPorContexto extends JPanel {

    /** combobox con contextos **/
    private JComboBox<Contexto> cbContextos;
    /** lista de tareas, cambia segun contexto seleccionado */
    private ArrayList<TareaPanel> listaTareas;
    private Model model;
    private GridBagConstraints gbc;
    private JButton eliminar;
    private ArrayList<TareaPanel> tareaPanels;

    /** constructor */
    public VistaPorContexto(Model model) {
        this.model = model;
        tareaPanels = new ArrayList<>();
        setOpaque(false);
        Font font = new Font("Centhury Gothic",Font.PLAIN,20);
        setBorder(BorderFactory.createTitledBorder(
                        new LineBorder(Color.WHITE, 2),
                        "Contextos", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, font, Color.WHITE)
        );

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.gridx = 0;

        listaTareas = new ArrayList<>();
        cbContextos = new JComboBox<>();
        cbContextos.setSize(new Dimension(30, 10));
        inicializarCBContexto();
        cbContextos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        eliminar = new JButton(" X ");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.eliminarContexto((Contexto) cbContextos.getSelectedItem());
                update();
                cbContextos.setSelectedIndex(0);
            }
        });
        gbc.anchor = GridBagConstraints.NORTHWEST;
        update();
    }

    /** iniciliza el comboBox con los contextos */
    private void inicializarCBContexto() {
        //cbContextos = new JComboBox<>();
        cbContextos.removeAllItems();
        for (Contexto contexto : model.getContextos()) {
            cbContextos.addItem(contexto);
        }
    }

    public void actualizarContextos() {
        update();
    }

    /** actualiza lo que muestra */
    public void update() {
        Contexto ContextoActual = (Contexto) cbContextos.getSelectedItem();
        inicializarCBContexto();
        cbContextos.setSelectedItem(ContextoActual);
        /** sacamos todos los componentes */
        removeAll();
        /** agregamos el combo box */
        //gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(cbContextos, gbc);
        /** boton eliminar */
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridx = 2;
        add(eliminar, gbc);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        /** agregamos las tareas */
        listaTareas.clear();
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        agregarTareasALista();
        int i = 1;
        for (TareaPanel TP : listaTareas) {
            gbc.gridy = i++;
            add(TP, gbc);
        }
        updateUI();
    }

    /** muestra las tareas segun el contexto selecionado  */
    private void agregarTareasALista() {
        tareaPanels = new ArrayList<>();
        Contexto ContextoActual = (Contexto) cbContextos.getSelectedItem();
        for (Proyecto p : model.getProyectos()) {
            for (Tarea t : p.getTareas()) {
                if (t.getContexto().equals(ContextoActual)) {
                    TareaPanel TP = new TareaPanel(t,model);
                    tareaPanels.add(TP);
                    listaTareas.add(TP);
                }
            }
        }
    }

    public void setControllerListeners(ArrayList<ControllerListener> listeners) {
        for (ControllerListener listener : listeners) {
            for (TareaPanel TP : tareaPanels) {
                TP.addControllerListener(listener);
            }
        }
    }

}
