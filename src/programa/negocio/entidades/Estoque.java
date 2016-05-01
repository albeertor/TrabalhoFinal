package programa.negocio.entidades;

import java.util.Date;

public class Estoque {
	private long cod;
	private Produto p;
	private int estoque;
	private double vlVenda;
	private Date dtEntrada;

	private Estoque(Produto p, int estoque, double vlVenda, Date dtEntrada) {
		this.p = p;
		this.estoque = estoque;
		this.vlVenda = vlVenda;
		this.dtEntrada = dtEntrada;
	}

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public static Estoque newInstance(Produto p, int estoque, double vlVenda, Date dtEntrada) {
		return new Estoque(p, estoque, vlVenda, dtEntrada);
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public Produto getP() {
		return p;
	}

	public void setP(Produto p) {
		this.p = p;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getVlVenda() {
		return vlVenda;
	}

	public void setVlVenda(double vlVenda) {
		this.vlVenda = vlVenda;
	}

	public Date getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

}
