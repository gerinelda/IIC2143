
public class hora {
	int h;
	int m;
	int s;

	public hora(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
	}
	public hora(int h, int m) {
		this.h = h;
		this.m = m;
		this.s = 0;
	}
	public hora(int h) {
		this.h = h;
		this.m = 0;
		this.s = 0;
	}

	// falta corregir que muestre 2 numeros. (08 envez de 8)
	@Override
	public String toString() {
		return new String(
				Integer.toString(h)+
						":"+
						Integer.toString(m)+
						":"+
						Integer.toString(s)
		);

	}
}
