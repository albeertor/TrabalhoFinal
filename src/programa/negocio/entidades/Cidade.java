package programa.negocio.entidades;

public class Cidade {
	private long codCidade;
	private String nome;
	private String sgEstado;
	
	private Cidade(String nome, String sgEstado) {
		this.nome = nome;
		this.sgEstado = sgEstado;
	}
	
	public Cidade() {
	}

	public static Cidade newInstance(String nome, String sgEstado){
		return new Cidade(nome, sgEstado);
	}
	
	public long getCodCidade() {
		return codCidade;
	}
	public String getNome() {
		return nome;
	}
	public String getsgEstado() {
		return sgEstado;
	}
	
	public void setCodCidade(long codCidade) {
		this.codCidade = codCidade;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	
}
