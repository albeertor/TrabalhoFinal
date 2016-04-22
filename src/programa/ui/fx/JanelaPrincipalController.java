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
	private Button btFechar, btCliente, btProduto;
	@FXML
	private MenuItem itemClienteInserir, itemClienteControle, itemProdutoInserir, itemProdutoControle;

	private UICliente uiCliente;
	private UICidade uiCidade;
	private UIProduto uiProduto;

	public JanelaPrincipalController(UICliente uiCliente, UICidade uiCidade, UIProduto uiProduto) {
		this.uiCliente = uiCliente;
		this.uiCidade = uiCidade;
		this.uiProduto = uiProduto;
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
		
		btProduto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiProduto.listarProdutos();
			}
		});

		itemClienteInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiCliente.lerCliente();
			}
		});

		itemClienteControle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiCliente.listarClientes();
			}
		});
		
		itemProdutoInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiProduto.lerProduto();
			}
		});

		itemProdutoControle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Programa.uiProduto.listarProdutos();
			}
		});
		
		

	}
}
