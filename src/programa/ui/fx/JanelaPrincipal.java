package programa.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.dados.CidadeDAO;
import programa.dados.ClienteDAO;
import programa.dados.EstoqueDAO;
import programa.dados.IRepositorioCidade;
import programa.dados.IRepositorioCliente;
import programa.dados.IRepositorioEstoque;
import programa.dados.IRepositorioProduto;
import programa.dados.ProdutoDAO;
import programa.negocio.Controle;
import programa.ui.fx.cidade.UICidade;
import programa.ui.fx.cliente.UICliente;
import programa.ui.fx.estoque.UIEstoque;
import programa.ui.fx.produto.UIProduto;

public class JanelaPrincipal extends Application {
	private static Stage stage;

	private static UICliente uiCliente;
	private static UICidade uiCidade;
	private static UIProduto uiProduto;
	private static UIEstoque uiEstoque;

	public static UIProduto getUiProduto() {
		return uiProduto;
	}

	public static void setUiProduto(UIProduto uiProduto) {
		JanelaPrincipal.uiProduto = uiProduto;
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaPrincipal.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
		loader.setController(new JanelaPrincipalController(uiCliente, uiCidade, uiProduto, uiEstoque));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Tela Principal");
		stage.show();
	}

	public static void main(String[] args) {
		IRepositorioCliente repoCliente = new ClienteDAO();
		IRepositorioCidade repoCidade = new CidadeDAO();
		IRepositorioProduto repoProduto = new ProdutoDAO();
		IRepositorioEstoque repoEstoque = new EstoqueDAO();

		Controle ctr = new Controle(repoCliente, repoCidade, repoProduto, repoEstoque);

		uiCliente = new UICliente(ctr);
		uiCidade = new UICidade(ctr);
		uiProduto = new UIProduto(ctr);
		uiEstoque = new UIEstoque(ctr);

		launch();
	}

	public static UICidade getUiCidade() {
		return uiCidade;
	}

	public static void setUiCidade(UICidade uiCidade) {
		JanelaPrincipal.uiCidade = uiCidade;
	}

	public static UICliente getUiCliente() {
		return uiCliente;
	}

	public static void setUICliente(UICliente uiCliente) {
		JanelaPrincipal.uiCliente = uiCliente;
	}

}
