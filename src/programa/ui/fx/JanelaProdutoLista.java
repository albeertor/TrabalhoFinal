package programa.ui.fx;

import java.util.List;

import programa.negocio.entidades.Produto;

public class JanelaProdutoLista {

	private List<Produto> produtos;
	private UIProduto uiProduto;

	public JanelaProdutoLista(List<Produto> produtos, UIProduto uiProduto) {
		this.produtos = produtos;
		this.uiProduto = uiProduto;
	}

}
