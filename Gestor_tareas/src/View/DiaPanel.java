package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DiaPanel extends JPanel implements ActionListener {

    private int mes;
    private int dia;
    private int year;
    private ArrayList<TareaPanel> tareas = new ArrayList<>();
    private Model model;
    private int diaSemana;
    private TransparentButton agregar;
    private ArrayList<ControllerListener> controllerListeners;

    public DiaPanel(int dia, int mes, int year, Model model) {

        setPreferredSize(new Dimension(200, 140));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  this.dia = dia;
        setBorder(new EtchedBorder());

        controllerListeners = new ArrayList<>();
        this.model = model;

        this.mes = mes;
        this.year = year;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mes, dia);
        diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        int diaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);

        /** fondo depende del dia **/
        setBackground(getColor(diaSemana, dia, diaActual, mes, mesActual, year, yearActual));

        /** Botones **/
        String emptyOrZero = (dia < 10) ? "0" : "";
        String dia_string = emptyOrZero.concat(Integer.toString(dia));
        JLabel lFecha = new JLabel(" " + dia_string);
        agregar = new TransparentButton(" + ");
        agregar.setActionCommand("agregar");
        agregar.addActionListener(this);
        agregar.setBorder(new EmptyBorder(0, 0, 0, 0));
        lFecha.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        lFecha.setForeground(Color.white);

        JPanel Xlayout = new JPanel();
        Xlayout.setOpaque(false);
        Xlayout.setLayout(new BoxLayout(Xlayout, BoxLayout.LINE_AXIS));
        Xlayout.add(lFecha);
        Xlayout.add(agregar);
        Xlayout.setAlignmentX(Component.LEFT_ALIGNMENT);
        /** **/

        add(Xlayout);
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
    }

    /** agrega la tarea al dia **/
    public void addTarea(Tarea tarea) {
        TareaPanel tP = new TareaPanel(tarea);
        tareas.add(tP);
        add(tP);
    }

    /** revisa si la fecha del dia es igual a la fecha ingresada **/
    public boolean mismaFecha(Fecha fecha) {
        //mes (de Calendar.MONTH) parte en 0 hasta 11
        return (fecha.getD() == dia && fecha.getM() == mes+1 && fecha.getY() == year);
    }

    /** agrega a quien escucha la modificacion de una tarea
     * la diferencia con el action listener es que este entrega como
     * parametros el id de la tarea **/
    public void addControllerListener(ControllerListener listener) {
        if (tareas != null) {
            for (TareaPanel tarea : tareas) {
                tarea.addControllerListener(listener);
            }
        }
        controllerListeners.add(listener);
    }

    /** agrega un listener para una accion **/
    public void addListener(ActionListener listener) {
        if (tareas != null) {
            for (TareaPanel tarea : tareas) {
                tarea.addListener(listener);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("agregar")) {
            TareaRapidaFrame tareaRapidaFrame = new TareaRapidaFrame(model,new GregorianCalendar(year,mes,dia));
            tareaRapidaFrame.setListeners(controllerListeners);
            tareaRapidaFrame.setVisible(true);
        }
    }
}

