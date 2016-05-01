package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioProduto;
import programa.negocio.entidades.Produto;

public class ControladorProduto {
	private IRepositorioProduto repoProduto;

	public ControladorProduto(IRepositorioProduto repoProduto) {
		this.repoProduto = repoProduto;
	}

	public boolean inserir(Produto prod) {
		return repoProduto.inserir(prod);
	}

	public long proxCod() {
		return repoProduto.proxCod();
	}

	public boolean alterar(Produto prod) {
		return repoProduto.alterar(prod);
	}

	public List<Produto> getLista() {
		return repoProduto.getLista();
	}

	/*
	 * public List<Produto> getPesquisa(Produto prod) { return
	 * repoProduto.getPesquisa(prod); }
	 */

	public boolean excluir(Produto p) {
		return repoProduto.excluir(p);
	}

}
