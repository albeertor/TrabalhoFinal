package programa.ui.fx;

import javax.swing.JOptionPane;

import programa.negocio.Controle;
import programa.negocio.entidades.Produto;

public class UIProduto {
	private Controle ctr;

	public UIProduto(Controle controle) {
		this.ctr = controle;
	}

	public void listarProdutos() {
		// TODO Auto-generated method stub
		
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
			if (ctr.inserirProduto(prod))
				JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
			else
				JOptionPane.showMessageDialog(null, "Falha ao inserir produto");
		}
		
	}

	public void alterarProduto(Produto prod) {
		// TODO Auto-generated method stub
		
	}

}
