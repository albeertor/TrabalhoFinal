package programa.ui.fx.estoque;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Estoque;
import programa.negocio.entidades.Produto;
import programa.ui.fx.produto.UIProduto;

public class JanelaEstoque extends Application {
	private static Stage stage;
	private long proxCod;
	private List<Estoque> estoque;
	private UIEstoque uiEstoque;

	public JanelaEstoque(long proxCod, List<Estoque> estoque, UIEstoque uiEstoque) throws Exception {
		this.proxCod = proxCod;
		this.estoque = estoque;
		this.uiEstoque = uiEstoque;		
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaEstoque.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Estoque.fxml"));
		loader.setController(new JanelaEstoqueController(proxCod,estoque,uiEstoque));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Produto");
		stage.show();
	}
}
