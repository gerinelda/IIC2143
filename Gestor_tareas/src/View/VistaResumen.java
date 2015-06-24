package View;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class VistaResumen extends JFrame implements ControllerListener {
	
	private Model model;
	private CalendarioFrame calendario;
	private ArrayList<Proyecto> proyectos;
	private CreadorProyectos creadorProyectos;
	private CreadorTareas creadorTareas;
	private CreadorContextos creadorContextos;
	private ArrayList<ControllerListener> controllerListeners;
	private JPanel content;
	private ArrayList<Tarea> listaActualTareas;
	private JMenuBar menubar;
	private VistaPorContexto vistaPorContexto;
	private Image BGimage;
	private VistaSemanal vistaSemanal;
	private VistaExportarImportar vistaExportarImportar;
	private boolean viendoContextos;
	private boolean viendoTareas;

	public VistaResumen(Model model) {
		this.model = model;
		this.proyectos = model.getProyectos();
		controllerListeners = new ArrayList<>();
		controllerListeners.add(this);
		calendario = new CalendarioFrame(model);
		vistaSemanal = new VistaSemanal(model);
		vistaPorContexto = new VistaPorContexto(model);
		viendoContextos = false;
		viendoTareas = true;
		setSize(830, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents();

		mostrarNotificaciones();
	}

	private void placeComponents() {
		JPanel Horizontal = new JPanel();
		Horizontal.setLayout(new BoxLayout(Horizontal, BoxLayout.X_AXIS));

		/** imagen de fondo */
        try {
            BGimage = ImageIO.read(getClass().getResource("/resources/imagenes/calendarioBG.jpg"));

        } catch (Exception e) {
            setBackground(Color.getHSBColor(0.191F, 0.3F, 0.21F));
            System.out.println(e);
            System.out.println("Background image loading failed.");
        }
		content = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(BGimage, 0, 0, this);
			g.setColor(new Color(119, 2, 20, 160));
			g.fillRect(0, 0, getWidth(), getHeight());
		}};


		menubar = new JMenuBar();
		JButton addBtn = new JButton(" + ");
		JButton btn0 = new JButton("Todas");
		JButton btn1 = new JButton("Pendientes");
		JButton btn2 = new JButton("Completadas");
		JButton btn3 = new JButton("Esta Semana");
		JButton btn4 = new JButton("Proyectos");
		JButton btn5 = new JButton("Contexto");

		JPanel sidebar = new JPanel();

		content.setLayout(new GridLayout(0, 1));
		sidebar.setLayout(new GridLayout(0, 1));
		sidebar.setMaximumSize(new Dimension(100, 100000));
		setJMenuBar(menubar);
		setContentPane(Horizontal);

		Horizontal.add(sidebar);
		Horizontal.add(content);
		sidebar.add(addBtn);
		sidebar.add(btn0);
		sidebar.add(btn1);
		sidebar.add(btn2);
		sidebar.add(btn3);
		sidebar.add(btn5);
		sidebar.add(btn4);

		Font font = new Font("Centhury Gothic",Font.PLAIN,20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Vista Resumen", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));

		//okay: faltan dias de la semana
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TareaRapidaFrame TRF = new TareaRapidaFrame(model,Calendar.getInstance());
				TRF.setListeners(controllerListeners);
				TRF.setVisible(true);
			}
		});

		/** BOTONES ( FILTROS / SORT ) **/
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getTareasPorFechaFinal();
				mostrarTareas(listaActualTareas);
			}
		});
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getTareasPendientes();
				mostrarTareas(listaActualTareas);
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getTareasCompletadas();
				mostrarTareas(listaActualTareas);
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getProximosDias(7);
				mostrarTareas(listaActualTareas);
			}
		});


		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarProyectos();
			}
		});
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarContextos();
			}
		});
		/** Content **/
		JLabel nombreLabel = new JLabel("VISTA RESUMEN");
		content.add(nombreLabel);
		listaActualTareas = getTareasPorFechaFinal();
		mostrarTareas(listaActualTareas);

		JButton crearTareaButton = new JButton("+Tarea");
		menubar.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("+Proyecto");
		menubar.add(crearProyectoButton);

		JButton crearContextoButton = new JButton("+Contexto");
		menubar.add(crearContextoButton);

		JButton vistaCalendarioButton = new JButton("Calendario");
		menubar.add(vistaCalendarioButton);

		JButton vistaSemanalButton = new JButton("Semanas");
		menubar.add(vistaSemanalButton);

		JButton ExportarButton = new JButton("Importar/Exportar");
		menubar.add(ExportarButton);
		ExportarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** Ventana IMPORTAR / EXPORTAR **/
				vistaExportarImportar = new VistaExportarImportar(model);
			}
		});

		crearTareaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creadorTareas = new CreadorTareas(model);
				for (ControllerListener listener : controllerListeners) {
					creadorTareas.addControllerListener(listener);
				}
				creadorTareas.setVisible(true);
			}
		});

		crearProyectoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creadorProyectos = new CreadorProyectos(model);
				for (ControllerListener listener : controllerListeners) {
					creadorProyectos.addControllerListener(listener);
				}
				creadorProyectos.setVisible(true);
			}
		});

		crearContextoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creadorContextos = new CreadorContextos(model);
				for (ControllerListener listener : controllerListeners) {
					creadorContextos.addControllerListener(listener);
				}
				creadorContextos.setVisible(true);
			}
		});

		vistaCalendarioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calendario.setVisible(true);
			}
		});

		vistaSemanalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaSemanal.setVisible(true);
			}
		});
	}

	public void mostrarTareas(ArrayList<Tarea> tareas) {
		viendoTareas = true;
		viendoContextos = false;
		content.removeAll();
		JPanel subContent = new JPanel();
		subContent.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		int i = 0;
		subContent.setOpaque(false);
		subContent.setVisible(true);
		for (Tarea t : tareas) {
			TareaPanel TP = new TareaPanel(t,model);
			TP.mostrarTodo();
			for (ControllerListener listener : controllerListeners) {
				TP.addControllerListener(listener);
			}
			gbc.gridy = i++;
			subContent.add(TP,gbc);
		}
		content.add(subContent);
		content.updateUI();
	}

	public void setListener(ActionListener listener) {
		calendario.setListener(listener);
	}

	public ArrayList<Tarea> getTareasCompletadas() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (Proyecto p : proyectos) {
			for (Tarea t : p.getTareas()) {
				if (t.getEstado().equals(Estado.terminado)) {
					lista.add(t);
				}
			}
		}
		return lista;
	}

	public ArrayList<Tarea> getTareasPendientes() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for(int i = 0; i< model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				if ((t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR)
					+ 365 * t.getFf().getY())
					> (365*Calendar.getInstance().get(Calendar.YEAR)
						+ Calendar.getInstance().get(Calendar.DAY_OF_YEAR))) {
					if (!t.getEstado().equals(Estado.terminado)) {
						lista.add(t);
					}
				}
			}
		}
		return lista;
	}

	public ArrayList<Tarea> getProximosDias(int m) {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (int i = 0; i < model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				/** devolver solo tareas que vencen en los m=3 proximos dias **/
				int n = t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
				if ( n >= 0 && n <= m) {
					lista.add(t);
				}
			}
		}
		return lista;
	}

	public ArrayList<Tarea> getTareasPorFechaFinal() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (int i = 0; i < model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				lista.add(t);
			}
		}
		/** ordena lista segun el metodo CompareTo de Tarea,
		 * eso es porque la lista es de un tipo que implemente la interfaz Comparable **/
		Collections.sort(lista);
		return lista;
	}

	public void addControllerListener(ControllerListener listener) {
		calendario.addControllerListener(listener);
		for (ControllerListener controllerListener : controllerListeners) {
			if (listener.equals(controllerListener)) {
				return;
			}
		}
		controllerListeners.add(listener);
	}

	public void updateAll() {
		vistaPorContexto.actualizarContextos();
		if (viendoContextos) {
			mostrarContextos();
		}
		if (viendoTareas) {
			mostrarTareas(getTareasPorFechaFinal());
		}
		calendario.updateUI();
	}

	@Override
	public void ModificarTarea(ActionEvent e, Tarea tarea, Proyecto proyecto) {
		if (e.getActionCommand().equals("eliminar")) {
			/** eliminar de la lista **/

			/** creamos una copia de la lista (para evitar concurrent modification exception **/
			ArrayList<Tarea> listaNueva = new ArrayList<>();
			for (Tarea t : listaActualTareas) {
				listaNueva.add(t);
			}

			/** sacamos la tarea eliminada de la lista **/
			int n = 0;
			for (Tarea t : listaActualTareas) {
				if (tarea.equals(t)) {
					listaNueva.remove(n);
				}
				n++;
			}

			listaActualTareas = listaNueva;
			/** actualizamos graficamente **/
			calendario.updateUI();
			mostrarTareas(listaNueva);
		}
		else if (e.getActionCommand().equals("estado")) {
		}
	}

	public void mostrarContextos() {
		viendoContextos = true;
		viendoTareas = false;
		content.removeAll();
		vistaPorContexto = new VistaPorContexto(model);
		vistaPorContexto.setControllerListeners(controllerListeners);
		content.add(vistaPorContexto);
		content.updateUI();
	}

	public void mostrarProyectos() {
		content.removeAll();
		ArrayList<Proyecto> proyectos = model.getProyectos();
		for (Proyecto p : proyectos) {
			ProyectoPanel PP = new ProyectoPanel(p,model);
			content.add(PP);
		}
		content.updateUI();
	}

	public void actualizarProyectos() {
		vistaExportarImportar.actualizarProyectos();
	}

	public void mostrarNotificaciones() {
		JFrame notificaciones = new JFrame();
		notificaciones.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		int y = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		notificaciones.getContentPane().setBackground(new Color(88, 2, 20, 220));
		ArrayList<Tarea> listaTareasNotificadas = new ArrayList<>();
		for (Proyecto p : model.getProyectos()) {
			for (Tarea t : p.getTareas()) {
				if (!t.getEstado().equals(Estado.terminado)
					&& ( Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR) >= 0 )
						&& (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - t.getFf().getCalendario().get(Calendar.DAY_OF_YEAR) <= 7)) {
					listaTareasNotificadas.add(t);
				}
			}
		}
		if (listaTareasNotificadas.isEmpty()) {
			return;
		}
		setVisible(true);

		for (Tarea t : listaTareasNotificadas) {
			TareaPanel TP = new TareaPanel(t,model);
			for (ControllerListener listener : controllerListeners) {
				TP.addControllerListener(listener);
			}
			gbc.gridy = y++;
			notificaciones.getContentPane().add(TP,gbc);
		}
	}




}

