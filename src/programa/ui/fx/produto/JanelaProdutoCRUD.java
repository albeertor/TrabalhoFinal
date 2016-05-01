package programa.ui.fx.produto;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Produto;

public class JanelaProdutoCRUD extends Application{
	private static Stage stage;
	private Produto p = null;
	private long proxCod;
	private UIProduto uiProduto;
	private List<Produto> produtos;

	public JanelaProdutoCRUD(long proxCod, List<Produto> produtos, UIProduto uiProduto) throws Exception {	
		this.proxCod = proxCod;
		this.uiProduto = uiProduto;
		this.produtos = produtos;
		start(new Stage());
	}


	@Override
	public void start(Stage stage) throws Exception {
		JanelaProdutoCRUD.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CRUDProduto.fxml"));
		loader.setController(new JanelaProdutoCRUDController(proxCod,uiProduto, produtos));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Produto");
		stage.show();
	}

}
