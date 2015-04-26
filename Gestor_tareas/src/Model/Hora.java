package Model;

import java.io.Serializable;


public class Hora implements Serializable{
	int h;
	int m;
	int s;

	public Hora(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
	}
	public Hora(int h, int m) {
		this.h = h;
		this.m = m;
		this.s = 0;
	}
	public Hora(int h) {
		this.h = h;
		this.m = 0;
		this.s = 0;
	}
	Hora(){}

	@Override
	public String toString() {
		return new String(atToString(h)+":"+atToString(m)+":"+atToString(s));

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
