package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioEstoque;
import programa.negocio.entidades.Estoque;

public class ControladorEstoque {

	private IRepositorioEstoque repoEstoque;

	public ControladorEstoque(IRepositorioEstoque repoEstoque) {
		this.repoEstoque = repoEstoque;
	}

	public List<Estoque> getListaEstoque() {
		return repoEstoque.getLista();
	}

	public long proxCodEstoque() {
		return repoEstoque.proxCodEstoque();
	}

}
