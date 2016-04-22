package programa.negocio.entidades;

public class Produto {
	private long cod;
	private String nome;
	private String desc;
	private double vlUnit;
	private int qtd;
		
	private Produto(String nome, double vlUnit, int qtd, String desc) {
	
		this.nome = nome;
		this.vlUnit = vlUnit;
		this.qtd = qtd;
		this.desc = desc;
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

	public static Produto newInstance(String nome, double vlUnit, int qtd, String desc){
		return new Produto(nome,vlUnit,qtd,desc);
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
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	
}
