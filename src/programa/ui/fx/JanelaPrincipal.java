package programa.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JanelaPrincipal extends Application {
	private static Stage stage;

	private Button btEntrar;

	private UICliente uiCliente;
	private UICidade uiCidade;

	public JanelaPrincipal() {
	}

	public JanelaPrincipal(UICliente uiCliente, UICidade uiCidade) {
		this.uiCliente = uiCliente;
		this.uiCidade = uiCidade;
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaPrincipal.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
		JanelaPrincipalController j = new JanelaPrincipalController(uiCliente, uiCidade);

		loader.setController(j);
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Tela Principal");
		stage.show();
	}

}
