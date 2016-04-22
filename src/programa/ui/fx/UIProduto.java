package programa.ui.fx;

import java.util.List;

import javax.swing.JOptionPane;

import programa.negocio.Controle;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Produto;

public class UIProduto {
	private Controle ctr;

	public UIProduto(Controle controle) {
		this.ctr = controle;
	}

	public void lerProduto() {
		long proxCod = ctr.proxCod();
		
		JanelaProdutoInserir j = null;
		try {
			j = new JanelaProdutoInserir(proxCod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inserirProduto(Produto prod) {
		if (prod != null) {
			if (ctr.inserir(prod))
				JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
			else
				JOptionPane.showMessageDialog(null, "Falha ao inserir produto");
		}
		
	}

	public void alterarProduto(Produto prod) {
		if (ctr.alterar(prod))
			JOptionPane.showMessageDialog(null, "Cliente Alterado");
		else
			JOptionPane.showMessageDialog(null, "Falha ao alterar cliente");
	}
	
	public void listarProdutos() {
		List<Produto> produtos = ctr.getListaProdutos();
		try {
			JanelaProdutoLista j = new JanelaProdutoLista(produtos, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Produto> listarPesquisa(Produto prod) {
		return ctr.getPesquisa(prod);
	}

}
