package programa.ui.fx;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

public class JanelaCidadeLista extends Application{
	private static Stage stage;
	private UICidade uiCidade;
	private List<Cidade> listaCidade;

	public JanelaCidadeLista(List<Cidade> listaCidade, UICidade uiCidade) throws Exception {
		this.uiCidade = uiCidade;
		this.listaCidade = listaCidade;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaCidadeLista.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListarCidade.fxml"));
		loader.setController(new JanelaCidadeListaController(uiCidade, listaCidade));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Cliente");
	
		stage.show();
	}
}
