package programa.ui.fx;

import javafx.scene.control.MenuItem;
import programa.ui.fx.cidade.UICidade;
import programa.ui.fx.cliente.UICliente;
import programa.ui.fx.estoque.JanelaEstoque;
import programa.ui.fx.estoque.UIEstoque;
import programa.ui.fx.produto.UIProduto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class JanelaPrincipalController implements Initializable {
	@FXML
	private Button btFechar, btCliente, btProduto, btCidade, btVenda, btPagamento, btEstoque;
	@FXML
	private MenuItem itemClienteInserir, itemClienteControle, itemProdutoInserir, itemProdutoControle,itemCidadeInserir, itemCidadeControle;

	private UICliente uiCliente;
	private UICidade uiCidade;
	private UIProduto uiProduto;
	private UIEstoque uiEstoque;

	public JanelaPrincipalController(UICliente uiCliente, UICidade uiCidade, UIProduto uiProduto, UIEstoque uiEstoque) {
		this.uiCliente = uiCliente;
		this.uiCidade = uiCidade;
		this.uiProduto = uiProduto;
		this.uiEstoque = uiEstoque;
	}

	public void initialize(URL url, ResourceBundle bundle) {
		btFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
				// JOptionPane.showMessageDialog(null, "Mensagem");
			}
		});
		
		btEstoque.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiEstoque.listarEstoque();
			}
		});
		
		btCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				uiCliente.listarClientes();
			}
		});
		
		btProduto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiProduto.listarProdutos();
			}
		});
		
		btCidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCidade.listarCidade();
			}
		});

		itemCidadeInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCidade.lerCidade();
			}
		});
		
		itemCidadeControle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCidade.listarCidade();
			}
		});
		
		itemClienteInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCliente.lerCliente();
			}
		});

		itemClienteControle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCliente.listarClientes();
			}
		});
		
		itemProdutoInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiProduto.lerProduto();
			}
		});

		itemProdutoControle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiProduto.listarProdutos();
			}
		});
		
		

	}

}
