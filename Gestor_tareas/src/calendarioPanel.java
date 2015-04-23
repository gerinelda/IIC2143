import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class calendarioPanel extends JPanel {

    private JLabel dia;
    private List<tarea> listaTareas;
    ArrayList<diaPanel> diasPanel;
    Gestor g;
    calendarioFrame cF;

    public calendarioPanel(Calendar calendario, Gestor g, calendarioFrame cF) {
        this.cF = cF;
        this.g = g;
        diasPanel = new ArrayList<diaPanel>();
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
            add(new diaPanel());
        }
        for (int i = 1; i <= diasEnMes; i++) {
            diaPanel diapanel = new diaPanel(i,mes,year,g);
            //lo agregamos a calendarioPanel
            add(diapanel);
            //lo agregamos al array de dias
            diasPanel.add(diapanel);
            ArrayList<proyecto> proyectos = g.getProyectos();
            ArrayList<tarea> tareas;
            // for each proyecto in proyectos
                //for each tarea in tareas
                    // si misma fecha
                    // agregar tarea al dia panel (esto del spanglish..)
            for (proyecto p : proyectos) {
                tareas = p.getTareas();
                for (tarea t : tareas) {
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
