package programa.negocio;

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
}
