package programa.ui.fx.estoque;

import java.util.List;

import javafx.scene.control.Alert;
import programa.negocio.Controle;
import programa.negocio.entidades.Estoque;
import programa.ui.fx.produto.JanelaProdutoCRUD;

public class UIEstoque {
	private Controle ctr;
	private Alert alert;

	public UIEstoque(Controle controle) {
		this.ctr = controle;
	}

	public List<Estoque> getListaEstoque() {
		return ctr.getListaEstoque();
	}

	public void listarEstoque() {
		try {
			JanelaEstoque j = new JanelaEstoque(ctr.proxCodEstoque(), getListaEstoque(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
