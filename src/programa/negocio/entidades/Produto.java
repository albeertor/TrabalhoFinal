package programa.negocio.entidades;

public class Produto {
	private long cod;
	private String nome;
	private String desc;
	private double vlUnit;

	private Produto(String nome, double vlUnit, String desc) {
	
		this.nome = nome;
		this.vlUnit = vlUnit;
		this.desc = desc;
	}

	private Produto(String nome) {
		this.nome = nome;

	}

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Produto newInstance(String nome, double vlUnit, String desc) {
		return new Produto(nome, vlUnit, desc);
	}

	public static Produto newInstance(String nome) {
		return new Produto(nome);
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getVlUnit() {
		return vlUnit;
	}

	public void setVlUnit(double vlUnit) {
		this.vlUnit = vlUnit;
	}

}
