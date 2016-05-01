package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioCidade;
import programa.negocio.entidades.Cidade;

public class ControladorCidade {
	private IRepositorioCidade repoCidade;
	
	public ControladorCidade(IRepositorioCidade repoCidade) {
		this.repoCidade = repoCidade;
	}

	public List<Cidade> getLista() {
		return repoCidade.getLista();
	}

	public Cidade getCidade(int cdCidade) {
		return repoCidade.getCidade(cdCidade);
	}	

	public boolean inserir(Cidade c) {
		return repoCidade.inserir(c);
	}
	
	public boolean alterar(Cidade c) {
		return repoCidade.alterar(c);
	}
	
	public boolean excluir(Cidade c) {
		return repoCidade.excluir(c);
	}

	public long getProxId() {
		return repoCidade.getProxId();
	}
	
	public List<String> getEstado(){
		return repoCidade.getEstado();
	}
	
	public List<String> getListaEstado() {
		return repoCidade.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return repoCidade.getListaCest(sgEst);
	}
/*
	public List<Cidade> getPesquisa(Cidade c) {
		return repoCidade.getPesquisa(c);
	}
	*/

	public List<Cidade> getListaCod() {
		return repoCidade.getListaCod();
	}
}
