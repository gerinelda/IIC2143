import java.io.Serializable;

public class fecha implements Serializable {
	int d;
	int m;
	int y;

	public fecha(int d, int m, int y) {
		this.d = d;
		this.m = m;
		this.y = y;
	}
	
	fecha(){}

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