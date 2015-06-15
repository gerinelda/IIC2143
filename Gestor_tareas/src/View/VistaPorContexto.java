package View;

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
    private TransparentButton eliminar;

    /** constructor */
    public VistaPorContexto(Model model) {
        this.model = model;
        //setBackground(new Color(20, 35, 20, 230));
        setOpaque(false);
        Font font = new Font("Centhury Gothic",Font.PLAIN,20);
        setBorder(BorderFactory.createTitledBorder(
                        new LineBorder(Color.WHITE, 2),
                        "Vista por contextos", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, font, Color.WHITE)
        );

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;

        listaTareas = new ArrayList<>();
        cbContextos = new JComboBox<>();
        cbContextos.setSize(new Dimension(30, 10));
        inicializarCBContexto();
        update();
        cbContextos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        add(cbContextos, gbc);

        eliminar = new TransparentButton(" X ");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("oli");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(eliminar,gbc);

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
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(cbContextos, gbc);
        /** agregamos las tareas */
        listaTareas.clear();
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
        Contexto ContextoActual = (Contexto) cbContextos.getSelectedItem();
        for (Proyecto p : model.getProyectos()) {
            for (Tarea t : p.getTareas()) {
                if (t.getContexto().equals(ContextoActual)) {
                    TareaPanel TP = new TareaPanel(t,model);
                    listaTareas.add(TP);
                }
            }
        }
    }

}
