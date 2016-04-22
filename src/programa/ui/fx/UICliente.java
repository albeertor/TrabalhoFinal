package programa.ui.fx;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import programa.negocio.Controle;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

import programa.ui.fx.JanelaClienteInserir;
import programa.ui.fx.JanelaClienteLista;

public class UICliente {
	private Controle ctr;
	private Alert alert;

	public List<Cidade> getListaCidade() {
		return ctr.getListaCidades();
	}

	public List<Cliente> getListaCliente() {
		return ctr.getListaCliente();
	}

	public UICliente(Controle controle) {
		this.ctr = controle;
	}

	public void inserirCliente(Cliente c) {
		if (c != null) {
			if (ctr.inserir(c)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cliente");
				alert.setHeaderText(null);
				alert.setContentText("Inserido com sucesso!");
				alert.showAndWait();
			}
		}
	}

	public void lerCliente() {
		long proxId = ctr.proxIdCliente();

		JanelaClienteInserir j = null;
		try {
			j = new JanelaClienteInserir(proxId, getListaCidade());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarClientes() {
		List<Cliente> clientes = ctr.getListaCliente();
		try {
			JanelaClienteLista j = new JanelaClienteLista(clientes, getListaCidade(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> listarPesquisa(Cliente c) {
		return ctr.getPesquisa(c);
	}

	public void alterar(Cliente c) {
		if (c != null) {
			if (ctr.alterar(c)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cliente");
				alert.setHeaderText(null);
				alert.setContentText("Excluido com sucesso!");
				alert.showAndWait();
			}
		}
	}

	public void alterarCliente(Cliente c) {
		if (c != null) {
			JanelaClienteInserir jc = null;
			try {
				jc = new JanelaClienteInserir(c, getListaCidade());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void excluirCliente(Cliente c) {
		if (c != null) {
			if (ctr.excluir(c)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cliente");
				alert.setHeaderText(null);
				alert.setContentText("Excluido com sucesso!");
				alert.showAndWait();
			}
		}
	}
}
