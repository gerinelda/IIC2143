package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Model.*;

import java.awt.*;
import java.util.ArrayList;

/** clase no usada, a.k.a.: perdida de tiempo */
public class VistaTareas extends JPanel {

    private Model model;
    private int nProximosDias;
    public enum Filtro {NDIAS, EXPIRADAS, PENDIENTES, TODAS, NONE};
    private Filtro filtro;
    private ArrayList<Tarea> listaTareas;
    private GridBagConstraints gbc;

    public VistaTareas(Model model) {
        this.model = model;
        filtro = Filtro.NONE;
        listaTareas = new ArrayList<>();
        setVisible(true);
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        update();
    }

    public VistaTareas(Model model, int nDias) {
        this.model = model;
        filtro = Filtro.NONE;
        listaTareas = new ArrayList<>();
        nProximosDias = nDias;
        setVisible(true);
        setOpaque(false);
    }

    public void setFiltro(int n) {
        switch (n) {
            case 1:
                filtro = Filtro.NDIAS;
                break;
            case 2:
                filtro = Filtro.EXPIRADAS;
                break;
            case 3:
                filtro = Filtro.PENDIENTES;
                break;
            case 4:
                filtro = Filtro.TODAS;
                break;
            default:
                System.out.println("debe ser entre 1 y 4");
                break;
        }
    }

    public void update() {
        String titulo = "";
        switch (filtro) {
            case NDIAS:
                actualizarNDias();
                titulo = "Proximos " + nProximosDias + " dias";
                break;
            case EXPIRADAS:
                actualizarExpiradas();
                titulo = "Expiradas";
                break;
            case PENDIENTES:
                actualizarPendientes();
                titulo = "Pendientes";
                break;
            case TODAS:
                actualizarTodas();
                titulo = "Todas";
                break;
            default:
                break;
        }
        Font font = new Font("Centhury Gothic",Font.PLAIN,20);
        setBorder(new TitledBorder(
                        new LineBorder(Color.WHITE, 2),
                        "Tareas", TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, font, Color.WHITE)
        );
    }

    private void actualizarNDias() {
        /** temporal */
        actualizarTodas();
    }

    private void actualizarExpiradas() {
        /** temporal */
        actualizarTodas();
    }

    private void actualizarPendientes() {
        /** temporal */
        actualizarTodas();
    }

    private void actualizarTodas() {
        int i = 0;
        for (Proyecto p : model.getProyectos()) {
            for (Tarea t : p.getTareas()) {
                gbc.gridy = i++;
                listaTareas.add(t);
                TareaPanel TP = new TareaPanel(t);
                add(TP, gbc);
                System.out.println("1");
            }
        }
        actualizarNDias();
    }

}

