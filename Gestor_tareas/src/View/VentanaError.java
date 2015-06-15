package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaError extends JFrame {

	public VentanaError(String msg) {
		setLayout(new FlowLayout());
		JLabel texto = new JLabel(msg);
		int fontSize = 16;
		Font font = new Font(texto.getFont().getName(),texto.getFont().getStyle(),fontSize);
		add(texto);
		setSize(300, 100);
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(btnAceptar);
		setVisible(true);

	}
}
