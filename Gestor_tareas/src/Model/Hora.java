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

	public Hora() {
		this.h = 0;
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
}
