package programa.negocio.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
	private long codCliente;
	private String nome;
	private Date dtNasc;
	private String tel;
	private String cpf;
	private String rg;
	private String cep;
	private String endereco;
	private Cidade cidade;
	
	private Cliente(String nome, Date dtAniversario,
			String tel, String cpf, String rg, String cep, String endereco,
			Cidade cidade){
		this.nome = nome;
		this.dtNasc = dtAniversario;
		this.tel = tel;
		this.cpf = cpf;
		this.rg = rg;
		this.cep = cep;
		this.endereco = endereco;
		this.cidade = cidade;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String nome, String cpf, Cidade cidade) {
		this.nome = nome;
		this.cpf = cpf;
		this.cidade = cidade;
	}

	public static Cliente newInstance(String nome, String cpf, Cidade cidade){
		return new Cliente(nome,cpf,cidade);
	}
	
	public static Cliente newInstance(String nome, Date dtAniversario,
			String tel, String cpf, String rg, String cep, String endereco,
			Cidade cidade){
		if(nome != null && dtAniversario != null)
			return new Cliente(nome, dtAniversario, tel, cpf, rg, cep, endereco, cidade);
		else 
			return null;
	}
	
	public long getCodCliente() {
		return codCliente;
	}
	public String getNome() {
		return nome;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public String getTel() {
		return tel;
	}
	public String getCpf() {
		return cpf;
	}
	public String getRg() {
		return rg;
	}
	public String getCep() {
		return cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDtAniversario(Date dtAniversario) {
		this.dtNasc = dtAniversario;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setCodCliente(long cod) {
		this.codCliente = cod;
	}
	
	public String getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dtNasc);
}
	
}
