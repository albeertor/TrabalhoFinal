package programa.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JanelaPrincipal extends Application {
	private static Stage stage;

	private UICliente uiCliente;
	private UICidade uiCidade;
	private UIProduto uiProduto;

	public JanelaPrincipal(UICliente uiCliente, UICidade uiCidade, UIProduto uiProduto) {
		this.uiCliente = uiCliente;
		this.uiCidade = uiCidade;
		this.uiProduto = uiProduto;
		launch();
	}

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

}
