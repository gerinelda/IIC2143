package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import Model.*;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;
import net.fortuna.ical4j.util.UidGenerator;

public class CalendarioICS {
	
	public void importarCalendario(Model modelo, String path) throws IOException, ParserException
	{
		FileInputStream fin = new FileInputStream(path);
		
		CalendarBuilder builder = new CalendarBuilder();
		
		Calendar calendar = builder.build(fin);
		String p_name = calendar.getProperty("X-WR-CALNAME").getValue();
		Proyecto p = new Proyecto(modelo.getId_proyectos(), p_name , Estado.activo);
		modelo.agregarProyecto(p);
		for (Iterator e = calendar.getComponents().iterator(); e.hasNext();) {
			Component event = (Component) e.next();
			String name = event.getProperty("Summary").getValue();
			String dtstart = event.getProperty("Dtstart").getValue();
			String dtend = event.getProperty("Dtend").getValue();
			Fecha fi = new Fecha(Integer.parseInt(dtstart.substring(6, 8)), Integer.parseInt(dtstart.substring(4, 6)), Integer.parseInt(dtstart.substring(0, 4)));
			Fecha ff = new Fecha(Integer.parseInt(dtend.substring(6, 8)), Integer.parseInt(dtend.substring(4, 6)), Integer.parseInt(dtend.substring(0, 4)));
			Hora hi = new Hora();
			Hora hf= new Hora();
			if(dtstart.length()>15 && dtend.length()>15)
			{
				hi = new Hora(Integer.parseInt(dtstart.substring(9,11)), Integer.parseInt(dtstart.substring(11,13)), Integer.parseInt(dtstart.substring(13,15)));
				hf = new Hora(Integer.parseInt(dtend.substring(9,11)), Integer.parseInt(dtend.substring(11,13)), Integer.parseInt(dtend.substring(13,15)));
			}
			String description = event.getProperty("Description").getValue();
			System.out.println("nombre:"+name+", fi:"+fi.toString()+", ff:"+ff.toString()+", hi:"+hi.toString()+", hf:"+hf.toString()+", description:"+description);
			Tarea t = new Tarea(modelo.getId_tareas(), name, fi, ff, hi, hf, description, modelo.getContextos().get(0));
			modelo.agregarTarea(t, p.getId());
		}	

		fin.close();
	}
	
	public void importarAgenda(Model modelo, String carpeta) throws IOException, ParserException{
		File f = new File(carpeta);
		if (f.exists())
		{ 
			File[] ficheros = f.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				importarCalendario(modelo, ficheros[i].toString());
			}
		}
	}

	public void exportarAgenda(Model modelo, String carpeta) throws IOException, ValidationException, URISyntaxException{
		Calendar calendar;
		Proyecto p_actual;
		Tarea t_actual;
		FileOutputStream fout;
		TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
		TimeZone timezone = registry.getTimeZone("America/Santiago");
		VTimeZone tz = ((net.fortuna.ical4j.model.TimeZone) timezone).getVTimeZone();
		for(int i = 0; i < modelo.getContador_proyectos();i++)
		{
			p_actual = modelo.getProyectos().get(i);
			calendar = new Calendar();
			calendar.getProperties().add(new ProdId("-//Gestor de tareas//IIC2143//ES"));
			calendar.getProperties().add(Version.VERSION_2_0);
			calendar.getProperties().add(CalScale.GREGORIAN);
			calendar.getProperties().add(Method.PUBLISH);
			calendar.getProperties().add(new net.fortuna.ical4j.model.property.XProperty("X-WR-CALNAME", p_actual.getNombre()));
			calendar.getProperties().add(new net.fortuna.ical4j.model.property.XProperty("X-WR-TIMEZONE", "America/Santiago"));

			for (int j = 0; j < p_actual.getTareas().size(); j++) {
				t_actual = p_actual.getTareas().get(j);

				java.util.Calendar cStart = java.util.Calendar.getInstance();
				cStart.setTimeZone(timezone);
				cStart.set(java.util.Calendar.YEAR, t_actual.getFi().getY());
				cStart.set(java.util.Calendar.MONTH, t_actual.getFi().getM());
				cStart.set(java.util.Calendar.DAY_OF_MONTH, t_actual.getFi().getD());
				/*
				cStart.set(java.util.Calendar.HOUR, t_actual.getHi().getH());
				cStart.set(java.util.Calendar.MINUTE, t_actual.getHi().getM());
				cStart.set(java.util.Calendar.SECOND, t_actual.getHi().getS());
				*/

				java.util.Calendar cEnd = java.util.Calendar.getInstance();
				cEnd = java.util.Calendar.getInstance();
				cEnd.set(java.util.Calendar.YEAR, t_actual.getFf().getY());
				cEnd.set(java.util.Calendar.MONTH, t_actual.getFf().getM());
				cEnd.set(java.util.Calendar.DAY_OF_MONTH, t_actual.getFf().getD());
				/*
				cEnd.set(java.util.Calendar.HOUR, t_actual.getHf().getH());
				cEnd.set(java.util.Calendar.MINUTE, t_actual.getHf().getM());
				cEnd.set(java.util.Calendar.SECOND, t_actual.getHf().getS());
				*/

				DateTime start = new DateTime(cStart.getTime());
				DateTime end = new DateTime(cEnd.getTime());

				VEvent evento = new VEvent(start, end, t_actual.getNombre());

				evento.getProperties().add(new net.fortuna.ical4j.model.property.Description(t_actual.getDescripcion()));
				evento.getProperties().add(new net.fortuna.ical4j.model.property.Organizer("hola"));
				UidGenerator ug = new UidGenerator(t_actual.getId()+"");
				evento.getProperties().add(ug.generateUid());
				calendar.getComponents().add(evento);
			}
			if(calendar.getComponents().size()>0){
				fout = new FileOutputStream(carpeta+"/"+p_actual.getNombre()+".ics");
				CalendarOutputter outputter = new CalendarOutputter();
				outputter.output(calendar, fout);	
				fout.close();
			}
		}	
	}
	
	public void exportarSesion(Model modelo) throws IOException, ValidationException, URISyntaxException, ParseException{
		  String carpeta = "./calendarios";
		  exportarAgenda(modelo, carpeta);
	}

	public void importarSesion(Model modelo) throws IOException, ParserException{
		String sDirectorio = "./calendarios";
		File f = new File(sDirectorio);
		if (f.exists())
		{ 
			File[] ficheros = f.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				importarCalendario(modelo, ficheros[i].toString());
			}
		}
	}
}
