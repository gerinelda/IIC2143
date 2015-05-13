package View;
import Model.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class DiaPanel extends JPanel  {

    private int mes;
    private int dia;
    private int year;
    private ArrayList<TareaPanel> tareas = new ArrayList<>();
    private Model model;
    int diaSemana;

    public DiaPanel(int dia, int mes, int year, Model model) {


        setPreferredSize(new Dimension(200, 140));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  this.dia = dia;

        this.mes = mes;
        this.year = year;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mes, dia);

        diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        int diaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);

        /*
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black , 2),
                new TitledBorder(new LineBorder(Color.orange,1),Integer.toString(dia))
        ));

        */
        //setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.orange, 2), Integer.toString(dia)));
        String emptyOrZero = "";
        if (dia < 10) {
            emptyOrZero = "0";
        }
        String dia_string = emptyOrZero.concat(Integer.toString(dia));
        JLabel lFecha = new JLabel(" " + dia_string);
        Font labelFont = lFecha.getFont();
        //lFecha.setFont(new Font("Eras Light ITC",Font.PLAIN,28));
        lFecha.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        lFecha.setForeground(Color.white);

        setBackground(getColor(diaSemana, dia, diaActual, mes, mesActual, year, yearActual));

        setBorder(new EtchedBorder());

        add(lFecha);
        lFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

        setVisible(true);
        setOpaque(false);

    }


    public DiaPanel() {
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int d = 5;
        g.setColor(getBackground());
        g.fillRect(d,d,getWidth(),getHeight());
        g.setColor(getBackground().brighter());
        g.fillRect(0,0,d,getHeight());
        g.fillRect(d, 0, getWidth(), d);
    }

    private Color getColor(int diaSemana, int dia, int diaActual, int mes, int mesActual,int year, int yearActual) {
        Color color;
        if (year < yearActual || (year==yearActual && mes < mesActual) || (dia < diaActual && mes == mesActual && year==yearActual)) {
            //dias cumplidos
            color = new Color(47, 0, 8, 230);
        } else if (dia == diaActual && mes == mesActual && year == yearActual) {
            //dia actual
            color = new Color(80, 0, 15, 230);
        } else if (dia < 1) {
            //mes anterior
            color = new Color(35, 35, 35,230);
        } else {
            //futuros dias
            color = new Color(10, 19, 13, 230);
        }
        if (diaSemana == 1 || diaSemana == 7) {
            //fin de semanas
            color = color.darker().darker();
        }
        return color;
        //return new Color(36, 1, 9,230);
    }

    public void addTarea(Tarea tarea) {
        TareaPanel tP = new TareaPanel(tarea, model);
        tareas.add(tP);
        add(tP);
    }

    public boolean mismaFecha(Fecha fecha) {
        //mes (de Calendar.MONTH) parte en 0 hasta 11
        if (fecha.getD() == dia && fecha.getM() == mes+1 && fecha.getY() == year) {
            return true;
        } else return false;
    }

    public void setEliminarTareaListener(EliminarTareaListener listener) {
        if (tareas != null) {
            for (TareaPanel tarea : tareas) {
                tarea.setEliminarTareaListener(listener);
            }
        }
    }
}

