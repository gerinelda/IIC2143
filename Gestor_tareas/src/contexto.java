import java.io.Serializable;
import java.util.*;


public class contexto implements Serializable{
	
	private String nombre;

	public contexto(String contexto) {
		nombre = contexto;
	}

	public String getNombre() {
		return nombre;
	}


}
