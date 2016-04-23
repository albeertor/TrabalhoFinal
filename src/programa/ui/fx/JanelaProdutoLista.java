package programa.ui.fx;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Produto;

public class JanelaProdutoLista extends Application {

	private static Stage stage;
	private List<Produto> produtos;
	private UIProduto uiProduto;

	public JanelaProdutoLista(List<Produto> produtos, UIProduto uiProduto) throws Exception {
		this.produtos = produtos;
		this.uiProduto = uiProduto;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaProdutoLista.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListarProduto.fxml"));
		loader.setController(new JanelaProdutoListaController(produtos, uiProduto));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Produto");
	
		stage.show();
	}

}
