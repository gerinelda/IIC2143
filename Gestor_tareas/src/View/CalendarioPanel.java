package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarioPanel extends JPanel {

    private JLabel dia;
    private List<Tarea> listaTareas;
    ArrayList<DiaPanel> diasPanel;
    Gestor g;
    CalendarioFrame cF;

    public CalendarioPanel(Calendar calendario, Gestor g, CalendarioFrame cF) {
        this.cF = cF;
        this.g = g;
        diasPanel = new ArrayList<DiaPanel>();
        Calendar c = new GregorianCalendar();
        c.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.getActualMaximum(Calendar.MONTH));
        int weeks = c.get(Calendar.WEEK_OF_MONTH);
        setLayout(new GridLayout(0,7));
        addDays(calendario);
        setVisible(true);
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
            DiaPanel diapanel = new DiaPanel(i,mes,year,g);
            //lo agregamos a View.CalendarioPanel
            add(diapanel);
            //lo agregamos al array de dias
            diasPanel.add(diapanel);
            ArrayList<proyecto> proyectos = g.getProyectos();
            ArrayList<Tarea> tareas;
            // for each proyecto in proyectos
                //for each Tarea in tareas
                    // si misma Fecha
                    // agregar Tarea al dia panel (esto del spanglish..)
            for (proyecto p : proyectos) {
                tareas = p.getTareas();
                for (Tarea t : tareas) {
                    //System.out.print(Integer.toString(i)+"/"+Integer.toString(mes+1)+"/"+Integer.toString(year));
                    //System.out.print("   ");
                    //System.out.println(Integer.toString(t.getFf().getD())+"/"+Integer.toString(t.getFf().getM())+"/"+Integer.toString(t.getFf().getY()));
                    if (diapanel.mismaFecha(t.getFf())) {
                        diapanel.addTarea(t);
                    }
                }
            }
        }

    }

    public void update(Calendar calendario) {
        removeAll();
        addDays(calendario);
    }

}
