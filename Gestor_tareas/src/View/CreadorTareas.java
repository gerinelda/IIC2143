package View;

import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class CreadorTareas extends JFrame {

	private JTextField nombreText;
	private JComboBox<Proyecto> listaProyectos;
	private JTextField descripcionText;
	private Model model;
	private JComboBox<Contexto> listaContexto;
	private JComboBox<Integer> fidia;
	private JComboBox<Integer> fimes;
	private JComboBox<Integer> fiyear;
	private JComboBox<Integer> ffdia;
	private JComboBox<Integer> ffmes;
	private JComboBox<Integer> ffyear;
	private JTextField HIhora;
	private JTextField HIminuto;
	private JTextField HFhora;
	private JTextField HFminuto;
	private Calendar calendarioI;
	private Calendar calendarioF;
	private ArrayList<ControllerListener> controllerListeners;

	public CreadorTareas(Model model) {
		this.model = model;
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		controllerListeners = new ArrayList<>();
		JPanel panel = new JPanel();
		placeComponents(panel);
		add(panel);
		setVisible(true);
	}

	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		/** NOMBRE **/
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(10, 10, 80, 25);
		panel.add(nombreLabel);

		nombreText = new JTextField(20);
		nombreText.setBounds(100, 10, 160, 25);
		nombreText.setText("");
		panel.add(nombreText);

		/** FECHA INICIO **/
		int fechaWidth = 53;
		JLabel fiLabel = new JLabel("Fecha inicio");
		fiLabel.setBounds(10, 40, 80, 25);
		panel.add(fiLabel);

		calendarioI = Calendar.getInstance(); // calendario inicial
		fiyear = new JComboBox<>();
		fimes = new JComboBox<>();
		fidia = new JComboBox<>();
		/** year setup **/
		for (int i = 2015; i < 2035; i++) {
			fiyear.addItem(i);
		}
		/** month setup **/
		for (int i = 1; i <= 12; i++) {
			fimes.addItem(i);
		}
		/** day setup, se actualiza cuando se cambia el mes **/
		for (int i = 1; i <= 31; i++) {
			fidia.addItem(i);
		}
		fimes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarioI.set(calendarioI.get(Calendar.YEAR),((int) fimes.getSelectedItem())-1,calendarioI.get(Calendar.DAY_OF_MONTH));
				int maximum = calendarioI.getActualMaximum(Calendar.DAY_OF_MONTH);
				int removeDays = fidia.getItemCount() - maximum;
				if (removeDays > 0) { //sobran dias en el checkbox
					for (int n = fidia.getItemCount(); n > maximum; n--) {
						fidia.removeItemAt(n-1);
					}
				} else if (removeDays < 0) { // faltan dias en el checkbox
					for (int n = fidia.getItemCount(); n < maximum; n++) {
						fidia.addItem(n+1);
					}
				}
			}
		});
		fidia.setBounds(100, 40, fechaWidth, 25);
		fimes.setBounds(101+fechaWidth, 40, fechaWidth, 25);
		fiyear.setBounds(102+fechaWidth*2, 40, fechaWidth, 25);
		panel.add(fidia);
		panel.add(fimes);
		panel.add(fiyear);

		/** FECHA FINAL **/
		JLabel ffLabel = new JLabel("Fecha final");
		ffLabel.setBounds(10, 60, 80, 25);
		panel.add(ffLabel);

		calendarioF = Calendar.getInstance(); // calendario inicial
		ffyear = new JComboBox<>();
		ffmes = new JComboBox<>();
		ffdia = new JComboBox<>();
		/** year setup **/
		for (int i = 2015; i < 2035; i++) {
			ffyear.addItem(i);
		}
		/** month setup **/
		for (int i = 1; i <= 12; i++) {
			ffmes.addItem(i);
		}
		/** day setup, se actualiza cuando se cambia el mes **/
		for (int i = 1; i <= 31; i++) {
			ffdia.addItem(i);
		}
		ffmes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarioF.set(calendarioF.get(Calendar.YEAR), ((int) ffmes.getSelectedItem()) - 1, calendarioF.get(Calendar.DAY_OF_MONTH));
				int maximum = calendarioF.getActualMaximum(Calendar.DAY_OF_MONTH);
				int removeDays = ffdia.getItemCount() - maximum;
				if (removeDays > 0) { //sobran dias en el checkbox
					for (int n = ffdia.getItemCount(); n > maximum; n--) {
						ffdia.removeItemAt(n - 1);
					}
				} else if (removeDays < 0) { // faltan dias en el checkbox
					for (int n = ffdia.getItemCount(); n < maximum; n++) {
						ffdia.addItem(n + 1);
					}
				}
			}
		});
		ffdia.setBounds(100, 70, fechaWidth, 25);
		ffmes.setBounds(101 + fechaWidth, 70, fechaWidth, 25);
		ffyear.setBounds(102 + fechaWidth * 2, 70, fechaWidth, 25);
		panel.add(ffdia);
		panel.add(ffmes);
		panel.add(ffyear);

		HIhora = new JTextField("12");
		HIminuto = new JTextField("0");
		HFhora = new JTextField("12");
		HFminuto = new JTextField("0");

		JLabel hiLabel = new JLabel("Hora inicio");
		hiLabel.setBounds(10, 100, 80, 25);
		panel.add(hiLabel);

		HIhora.setBounds(100, 100, 40, 25);
		HIminuto.setBounds(142, 100, 40, 25);
		panel.add(HIhora);
		panel.add(HIminuto);

		JLabel hfLabel = new JLabel("Hora fin");
		hfLabel.setBounds(10, 130, 80, 25);
		panel.add(hfLabel);

		HFhora.setBounds(100, 130, 40, 25);
		HFminuto.setBounds(142, 130, 40, 25);
		panel.add(HFhora);
		panel.add(HFminuto);

		JLabel contextoLabel = new JLabel("Contexto");
		contextoLabel.setBounds(10, 160, 80, 25);
		panel.add(contextoLabel);

		listaContexto = new JComboBox();
		ArrayList<Contexto> arrayContexto = new ArrayList<>();
		if (model.getContextos()!=null) {
			arrayContexto = model.getContextos();
		}
		for (Contexto contexto : arrayContexto) {
			listaContexto.addItem(contexto);
		}
		listaContexto.setBounds(100,160,160,25);
		panel.add(listaContexto);

		JLabel proyectoLabel = new JLabel("Proyecto");
		proyectoLabel.setBounds(10, 190, 80, 25);
		panel.add(proyectoLabel);

		listaProyectos = new JComboBox<>();
		ArrayList<Proyecto> arrayProyectos = new ArrayList<>();
		if (this.model.getProyectos() != null) {
			arrayProyectos = this.model.getProyectos();
		}
		for (Proyecto p : arrayProyectos) {
			listaProyectos.addItem(p);
		}
		listaProyectos.setBounds(100, 190, 160, 25);
		panel.add(listaProyectos);


		JLabel descripcionLabel = new JLabel("Descripcion");
		descripcionLabel.setBounds(10, 220, 80, 25);
		panel.add(descripcionLabel);

		descripcionText = new JTextField(20);
		descripcionText.setBounds(100, 220, 160, 50);
		panel.add(descripcionText);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 290, 100, 25);
		panel.add(cancelarButton);

		JButton crearButton = new JButton("Crear");
		crearButton.setBounds(170, 290, 100, 25);
		panel.add(crearButton);

		crearButton.addActionListener(e -> {
            /** evento al controlador, agregar tarea **/
			agregarTarea(e);
        });
		crearButton.setActionCommand("agregar");

		cancelarButton.addActionListener(e -> setVisible(false));
	}

	public void addModificarTareaListener(ControllerListener listener) {

		// comprobar si ya estaba el listener en la lista
		for (ControllerListener controllerListener : controllerListeners) {
			if (controllerListener.equals(listener)) {
				return;
			}
		}
		// si no esta, lo agregamos
		controllerListeners.add(listener);
	}

	public void agregarTarea(ActionEvent e) {
		if (!nombreText.getText().isEmpty()
				&& new Integer(HIminuto.getText()) >= 0
				&& new Integer(HIminuto.getText()) < 60
				&& new Integer(HIhora.getText()) >= 0
				&& new Integer(HIhora.getText()) < 24
				&& new Integer(HFminuto.getText()) >= 0
				&& new Integer(HFminuto.getText()) < 60
				&& new Integer(HFhora.getText()) >= 0
				&& new Integer(HFhora.getText()) < 24) {

			int id = model.getId_tareas();
			String nombre = nombreText.getText();
			Fecha fi = new Fecha((int) fidia.getSelectedItem(),(int) fimes.getSelectedItem(),(int) fiyear.getSelectedItem());
			Fecha ff = new Fecha((int) ffdia.getSelectedItem(),(int) ffmes.getSelectedItem(),(int) ffyear.getSelectedItem());
			Hora hi = new Hora(new Integer(HIhora.getText()),new Integer(HIminuto.getText()));
			Hora hf = new Hora(new Integer(HFhora.getText()),new Integer(HFminuto.getText()));
			String descr = descripcionText.getText();
			int color = 0; //?????????????????
			Contexto contexto = (Contexto) listaContexto.getSelectedItem();

			Tarea tarea = new Tarea(id,nombre,fi,ff,hi,hf,descr,color,contexto);
			for (ControllerListener listener : controllerListeners) {
				listener.ModificarTarea(e, tarea, (Proyecto) listaProyectos.getSelectedItem());
			}
		}
	}
}
