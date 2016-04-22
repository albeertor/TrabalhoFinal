package programa.ui.fx;

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

	public JanelaProdutoInserir(Produto p, long proxCod) throws Exception {	
		this.p = p;
		this.proxCod = proxCod;
		start(new Stage());
	}
	
	public JanelaProdutoInserir(long proxCod) throws Exception {
		this.proxCod = proxCod;
		start(new Stage());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		JanelaProdutoInserir.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InserirProduto.fxml"));
		loader.setController(new JanelaProdutoInserirController(p,proxCod));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Produto");
		stage.show();
	}
}
