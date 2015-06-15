package View;
import Controller.*;

import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class View {

    private Model model;
    private VistaResumen vistaResumen;
    private ArrayList<String> emails;
    private ArrayList<EmailListener> emailListeners;

    public View(Model model) {
        this.model = model;
        vistaResumen = new VistaResumen(model);
        emails = new ArrayList<>();
        emailListeners = new ArrayList<>();

        JButton emailBtn = new JButton("ingresar e-mail");
        emailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame emailFrame = new JFrame();
                emailFrame.setVisible(true);
                JPanel content = new JPanel();
                emailFrame.setSize(300,150);
                emailFrame.setContentPane(content);
                content.add(new JLabel("Ingrese su email para recibir notificaciones"));
                JTextField textField;
                textField = new JTextField(20);
                content.add(textField);
                JButton boton_aceptar = new JButton("Aceptar");
                content.add(boton_aceptar);
                boton_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!textField.getText().isEmpty()) {
                            addEmail(textField.getText());
                        }
                        emailFrame.setVisible(false);
                    }
                });
            }
        });
        JButton emailBtn2 = new JButton("Enviar e-mail");
        emailBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame emailFrame = new JFrame();
                emailFrame.setVisible(true);
                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
                emailFrame.setSize(600, 150);
                emailFrame.setContentPane(content);
                if (emails.isEmpty()) {
                    content.add(new JLabel("No hay emails subscritos"));
                } else {
                    content.add(new JLabel("Esta seguro?"));
                    content.add(new JLabel(
                            "Esta accion enviara un e-mail con las tareas que \n" +
                            "vencen esta semana a los siguientes emails"));
                    if (emails!=null) {
                        for (String email : emails) {
                            content.add(new JLabel(email));
                        }
                    }
                }
                JButton boton_aceptar = new JButton("Aceptar");
                JButton boton_cancelar = new JButton("Cancelar");
                content.add(boton_aceptar);
                content.add(boton_cancelar);
                boton_aceptar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Enviando email");
                        enviarEmails();
                        emailFrame.setVisible(false);
                    }
                });
                boton_cancelar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        emailFrame.setVisible(false);
                    }
                });
            }
        });



        vistaResumen.getJMenuBar().add(emailBtn);
        vistaResumen.getJMenuBar().add(emailBtn2);


        vistaResumen.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(vistaResumen,
                        "Seguro que quieres salir?", "Salir",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Xml xml = new Xml();
                    xml.exportarSesion(model);
                    System.exit(0);
                }
            }
        });

        vistaResumen.setVisible(true);
    }

    public void addEmailListener(EmailListener listener) {
        emailListeners.add(listener);
    }

    private void enviarEmails() {
        String contenido = "";
        for (Proyecto p : model.getProyectos()) {
            for (Tarea t : p.getTareas()) {
                int diferencia =
                        (t.getFf().getCalendario().get(Calendar.YEAR)*365
                                + t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR))
                        -
                        (Calendar.getInstance().get(Calendar.YEAR)*365
                        + Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
                System.out.println(diferencia+"\t"+t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR)
                        +"\t"+Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
                if (diferencia >= 0 && diferencia <= 3) {
                    contenido+="Tarea ["+t.getNombre()+"] vence pronto!\n";
                }
            }
        }
        if (contenido.equals("")) {
            contenido = "No hay tareas por vencer en los proximos 3 dias";
        }

        for (String email : emails) {
            for (EmailListener listener : emailListeners) {
                listener.EnviarEmail(contenido,email,"Tareas pendientes");
            }
        }
    }

    public void addEmail(String email) {
        /** chequeamos si email es valido **/
        /** http://stackoverflow.com/questions/153716/verify-email-in-java **/
        Pattern rfc2822 = Pattern.compile(
                "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
        );
        if (!rfc2822.matcher(email).matches()) {
            System.out.println("invalid email");
        }
        /** **/


        else {
            emails.add(email);
            System.out.println("agregando email: " + email);
        }
    }

    public void setListener(ActionListener listener) {
        vistaResumen.setListener(listener);
    }

    public void addControllerListener(ControllerListener listener) {
        vistaResumen.addControllerListener(listener);
    }

    public void updateAll() {
        vistaResumen.updateAll();
    }

    public void actualizarProyectos() {
        vistaResumen.actualizarProyectos();
    }



}
