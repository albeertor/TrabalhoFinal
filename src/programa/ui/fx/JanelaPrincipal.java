package programa.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import programa.dados.CidadeDAO;
import programa.dados.ClienteDAO;
import programa.dados.IRepositorioCidade;
import programa.dados.IRepositorioCliente;
import programa.dados.IRepositorioProduto;
import programa.dados.ProdutoDAO;
import programa.negocio.Controle;

public class JanelaPrincipal extends Application {
	private static Stage stage;

	private static UICliente uiCliente;
	private static UICidade uiCidade;
	private static UIProduto uiProduto;

	@Override
	public void start(Stage stage) throws Exception {
		JanelaPrincipal.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));		
		loader.setController(new JanelaPrincipalController(uiCliente, uiCidade, uiProduto));
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
	
		Controle ctr = new Controle(repoCliente,repoCidade,repoProduto);
		
		uiCliente = new UICliente(ctr);
		uiCidade = new UICidade(ctr);
		uiProduto = new UIProduto(ctr);
		
		launch();
	}

}
