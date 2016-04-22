package programa.ui.swing;

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
}
