package View;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class View {

    private Model model;
    private VistaResumen vistaResumen;
    private ArrayList<String> emails;

    public View(Model model) {
        this.model = model;
        vistaResumen = new VistaResumen(model);
        emails = new ArrayList<>();

        JButton emailBtn = new JButton("e-mail");
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
        vistaResumen.getJMenuBar().add(emailBtn);
        vistaResumen.setVisible(true);
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

    public void addModificarTareaListener(ModificarTareaListener listener) {
        vistaResumen.addModificarTareaListener(listener);
    }

    public void updateAll() {
        vistaResumen.updateAll();
    }

    public void actualizarProyectos() {
        vistaResumen.actualizarProyectos();
    }


}
