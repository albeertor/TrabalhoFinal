package programa.ui.fx.cidade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;

public class JanelaCidadeInserir extends Application{
	private static Stage stage;
	private Cidade c = null;
	private long proxCod;
	private UICidade uiCidade;

	public JanelaCidadeInserir(Cidade c, UICidade uiCidade) throws Exception {	
		this.c = c;
		this.uiCidade = uiCidade;
		start(new Stage());
	}
	
	public JanelaCidadeInserir(long proxCod, UICidade uiCidade) throws Exception {
		this.proxCod = proxCod;
		this.uiCidade = uiCidade;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaCidadeInserir.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InserirCidade.fxml"));
		loader.setController(new JanelaCidadeInserirController(c,proxCod,uiCidade));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Cidade");
		stage.show();
	}
}
