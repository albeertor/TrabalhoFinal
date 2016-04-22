package programa.ui.fx;

import javafx.scene.control.MenuItem;
import programa.Programa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class JanelaPrincipalController implements Initializable {
	@FXML
	private Button btFechar, btInserir, btCliente;

	@FXML
	private MenuItem itemClienteInserir, itemClienteListar;

	private UICliente uiCliente;
	private UICidade uiCidade;

	public JanelaPrincipalController(UICliente uiCliente, UICidade uiCidade) {
		this.uiCliente = uiCliente;
		this.uiCidade = uiCidade;

	}

	public void initialize(URL url, ResourceBundle bundle) {
		btFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
				// JOptionPane.showMessageDialog(null, "Mensagem");
			}
		});
		
		btCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Programa.uiCliente.listarClientes();
			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Programa.uiCliente.lerCliente();
			}
		});

		itemClienteInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiCliente.lerCliente();
			}
		});

		itemClienteListar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiCliente.listarClientes();
			}
		});

	}
}
