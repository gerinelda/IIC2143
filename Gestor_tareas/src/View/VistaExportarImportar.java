package View;

import javax.swing.*;

import Controller.Xml;
import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaExportarImportar extends JFrame {

	private Model model;
	private JComboBox<Proyecto> proyectosCB;
	private JButton exportar;
	private JButton importar;
	private JPanel content;

	VistaExportarImportar(Model model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(new Dimension(250,200));

		/** content */
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setBackground(new Color(20, 35, 20, 230));

		/** inicializar proyectosCB */
		proyectosCB = new JComboBox<>();
		for (Proyecto p : model.getProyectos()) {
			proyectosCB.addItem(p);
		}

		/** botones */
		exportar = new JButton("Exportar");
		exportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Xml xml = new Xml();
				xml.crear((Proyecto) proyectosCB.getSelectedItem(), ((Proyecto) proyectosCB.getSelectedItem()).getNombre() + ".xml");
			}
		});
		importar = new JButton("Importar");
		importar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFileDialog();
			}
		});

		/** agregar elementos al frame */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		content.add(proyectosCB, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		content.add(exportar,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		content.add(importar,gbc);
		add(content);
	}

	private void openFileDialog() {
		FileDialog fd = new FileDialog(this,"Elije el archivo a importar",FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.xml");
		fd.setVisible(true);
		String ruta = fd.getFile();
		if (ruta != null) {
			Xml xml = new Xml();
			xml.leer(ruta, model);
		}
	}

	public void actualizarProyectos() {
		proyectosCB.removeAllItems();
		for (Proyecto p : model.getProyectos()) {
			proyectosCB.addItem(p);
		}
	}
}
