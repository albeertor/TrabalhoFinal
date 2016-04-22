package programa.ui.fx;

import java.util.List;

import programa.negocio.Controle;
import programa.negocio.entidades.Cidade;


public class UICidade {
	private Controle ctr;

	public UICidade(Controle controle) {
		this.ctr = controle;
	}	
	
	public List<Cidade> listarCidades() {
		List<Cidade> cidades = ctr.getListaCidades();
		return cidades;
		
	}

	public Cidade getCidade(int cdCidade) {
		return ctr.getCidade(cdCidade);
	}

	public List<String> getListaEstado() {
		return ctr.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return ctr.getListaCest(sgEst);
	}
}
