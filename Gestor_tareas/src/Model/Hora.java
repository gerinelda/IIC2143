package Model;

import java.io.Serializable;


public class Hora implements Serializable{
	private int h;
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}

	private int m;
	private int s;

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

	public Hora() {
		this.h = 12;
		this.m = 0;
		this.s = 0;

	}
	
	public Hora(String input){
		this.h = Integer.parseInt(input.substring(0,2));
		this.m = Integer.parseInt(input.substring(3, 5));
		this.s = Integer.parseInt(input.substring(6, 8));
	}
	
	@Override
	public String toString() {
		return atToString(h)+":"+atToString(m)+":"+atToString(s);

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

	public int getSegundos() {
		return s + 60 * m + 60 * 60 * h;
	}
}
