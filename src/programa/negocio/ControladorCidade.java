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

	public List<String> getListaEstado() {
		return repoCidade.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return repoCidade.getListaCest(sgEst);
	}
	
}
