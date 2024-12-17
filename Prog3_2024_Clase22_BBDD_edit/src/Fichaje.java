
public class Fichaje {
	private int idE;
	private int idJ;
	private float valorMercado;
	
	public Fichaje(int idE, int idJ, float valorMercado) {
		super();
		this.idE = idE;
		this.idJ = idJ;
		this.valorMercado = valorMercado;
	}

	public int getIdE() {
		return idE;
	}

	public void setIdE(int idE) {
		this.idE = idE;
	}

	public int getIdJ() {
		return idJ;
	}

	public void setIdJ(int idJ) {
		this.idJ = idJ;
	}

	public float getValorMercado() {
		return valorMercado;
	}

	public void setValorMercado(float valorMercado) {
		this.valorMercado = valorMercado;
	}

	@Override
	public String toString() {
		return "Fichaje [idE=" + idE + ", idJ=" + idJ + ", valorMercado=" + valorMercado + "]";
	}
}
