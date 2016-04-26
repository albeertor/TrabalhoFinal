package programa.ui.fx.produto;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import programa.negocio.Controle;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Produto;

public class UIProduto {
	private Controle ctr;
	private Alert alert;

	public UIProduto(Controle controle) {
		this.ctr = controle;
	}

	public void lerProduto() {
		long proxCod = ctr.proxCod();
		System.out.println(proxCod);
		JanelaProdutoInserir j = null;
		try {
			j = new JanelaProdutoInserir(proxCod,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserirProduto(Produto prod) {
		if (prod != null) {
			if (ctr.inserir(prod)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Produto");
				alert.setHeaderText(null);
				alert.setContentText("Inserido com sucesso!");
				alert.showAndWait();
			}
		}

	}

	public void alterarProduto(Produto p) {
		if (p != null) {
			JanelaProdutoInserir jc = null;
			try {
				jc = new JanelaProdutoInserir(p, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterar(Produto prod) {
		if (ctr.alterar(prod)) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Produto");
			alert.setHeaderText(null);
			alert.setContentText("Alterado com sucesso!");
			alert.showAndWait();
		}
	}

	public void listarProdutos() {
		List<Produto> produtos = ctr.getListaProdutos();
		try {
			JanelaProdutoLista j = new JanelaProdutoLista(produtos, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Produto> listarPesquisa(Produto prod) {
		return ctr.getPesquisa(prod);
	}

	public void excluir(Produto p) {
		if (p != null) {
			if (ctr.excluir(p)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Produto");
				alert.setHeaderText(null);
				alert.setContentText("Excluido com sucesso!");
				alert.showAndWait();
			}
		}
	}

}
