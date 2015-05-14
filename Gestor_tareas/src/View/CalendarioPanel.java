package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import Model.*;

import javax.imageio.ImageIO;

public class CalendarioPanel extends JPanel {

    private ArrayList<DiaPanel> diasPanel;
    private Model model;
    private BufferedImage BGimage;

    public CalendarioPanel(Calendar calendario, Model model, CalendarioFrame cF) {
        this.model = model;

        diasPanel = new ArrayList<>();

        setLayout(new GridLayout(0, 7));
        addDays(calendario);

        // imagen de fondo
        try {
            BGimage = ImageIO.read(CalendarioPanel.class.getResource("/Imagenes/calendarioBG.jpg"));

        } catch (IOException e) {
            System.out.println(e);
            setBackground(Color.getHSBColor(0.191F, 0.3F, 0.21F));
            System.out.println("Background image loading failed.");
        }

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setOpaque(false);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BGimage, 0, 0, this);
        g.setColor(new Color(119, 2, 20, 160));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        for (DiaPanel diaPanel : diasPanel) {
            diaPanel.addModificarTareaListener(listener);
        }
    }

     private void addDays(Calendar calendario) {
        int diasEnMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int year = calendario.get(Calendar.YEAR);
        Calendar c = new GregorianCalendar();
        c.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), 1);
        int diaInicial = (c.get(Calendar.DAY_OF_WEEK)+5)%7;

        for (int i = 0; i < diaInicial; i++) {
            add(new DiaPanel());
        }
        for (int i = 1; i <= diasEnMes; i++) {
            DiaPanel diaPanel = new DiaPanel(i,mes,year,model);
            //lo agregamos a VistaResumen.CalendarioPanel
            add(diaPanel);
            //lo agregamos al array de dias
            diasPanel.add(diaPanel);
            ArrayList<Proyecto> proyectos = model.getProyectos();
            ArrayList<Tarea> tareas;
            // for each Proyecto in proyectos
                //for each Model.Tarea in tareas
                    // si misma Fecha
                    // agregar Tarea al dia panel
            for (Proyecto p : proyectos) {
                tareas = p.getTareas();
                for (Tarea t : tareas) {
                    if (diaPanel.mismaFecha(t.getFf())) {
                        diaPanel.addTarea(t);
                    }
                }
            }
        }
    }

    public void setListener(ActionListener listener) {
        for (DiaPanel diaPanel : diasPanel) {
            diaPanel.addListener(listener);
        }
    }

    public void update(Calendar calendario) {
        removeAll();
        updateUI();
        addDays(calendario);
    }

}
