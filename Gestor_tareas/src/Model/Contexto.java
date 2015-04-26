package Model;

import java.io.Serializable;


public class Contexto implements Serializable{
	
	private String nombre;

	public Contexto(String contexto) {
		nombre = contexto;
	}

	public String getNombre() {
		return nombre;
	}


}
