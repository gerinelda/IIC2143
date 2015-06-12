package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha implements Serializable {

	Calendar calendar = Calendar.getInstance();
	int d;
	int m;
	int y;

	public Fecha(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}

	public Fecha() {
		this.d = calendar.get(Calendar.DAY_OF_MONTH);
		this.m = calendar.get(Calendar.MONTH)+1;
		this.y = calendar.get(Calendar.YEAR);
	}
	
	public Fecha(String input)
	{
		this.d = Integer.parseInt(input.substring(0,2));
		this.m = Integer.parseInt(input.substring(3, 5));
		this.y = Integer.parseInt(input.substring(6, 8));
	}

	/** fecha desde calendario **/
	public Fecha(Calendar calendario) {
		this.d = calendario.get(Calendar.DAY_OF_MONTH);
		this.m = calendario.get(Calendar.MONTH)+1;
		this.y = calendario.get(Calendar.YEAR);
	}

	public int getD() {
		return d;
	}

	public int getM() {
		return m;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		/** mes va de 0 a 11 **/
		return atToString(d)+"/"+(atToString(m)+1)+"/"+atToString(y);
	}
	public String atToString(int i)
	{
		if (i<10)
		{
			return "0"+Integer.toString(i);
		}
		else
		{
			return Integer.toString(i);
		}
	}

	public Calendar getCalendario() {
		return new GregorianCalendar(y,m-1,d);
	}
}