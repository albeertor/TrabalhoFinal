package programa.ui.fx.produto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Produto;


public class JanelaProdutoInserir extends Application{
	private static Stage stage;
	private Produto p = null;
	private long proxCod;
	private UIProduto uiProduto;

	public JanelaProdutoInserir(Produto p, UIProduto uiProduto) throws Exception {	
		this.p = p;
		this.uiProduto = uiProduto;
		start(new Stage());
	}
	
	public JanelaProdutoInserir(long proxCod, UIProduto uiProduto) throws Exception {
		this.proxCod = proxCod;
		this.uiProduto = uiProduto;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaProdutoInserir.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InserirProduto.fxml"));
		loader.setController(new JanelaProdutoInserirController(p,proxCod,uiProduto));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Produto");
		stage.show();
	}
}
