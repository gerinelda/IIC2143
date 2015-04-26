package Model;

import java.io.Serializable;
import java.util.Calendar;

public class Fecha implements Serializable {

	Calendar calendar = Calendar.getInstance();
	int d = calendar.get(Calendar.DAY_OF_MONTH);
	int m = calendar.get(Calendar.MONTH)+1; // Calendar.Month va de 0 a 11
	int y = calendar.get(Calendar.YEAR);

	public Fecha(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}

	Fecha(){}

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
		return new String(atToString(d)+"/"+atToString(m)+"/"+Integer.toString(y));
	}
	public String atToString(int i)
	{
		if (i<10)
		{
			return new String("0"+Integer.toString(i));
		}
		else
		{
			return new String(Integer.toString(i));
		}
	}

}