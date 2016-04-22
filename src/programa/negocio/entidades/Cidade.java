package programa.negocio.entidades;

public class Cidade {
	private long codCidade;
	private String nome;
	private String sgEstado;
	
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
