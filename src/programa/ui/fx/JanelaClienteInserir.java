package programa.ui.fx;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

public class JanelaClienteInserir extends Application {
	private static Stage stage;
	private List<Cidade> listaCidade;
	private long proxId;
	private Cliente c = null;

	public JanelaClienteInserir(long proxId, List<Cidade> listaCidade) throws Exception {
		this.proxId = proxId;
		this.listaCidade = listaCidade;		
		start(new Stage());
	}

	public JanelaClienteInserir(Cliente c, List<Cidade> listaCidade) throws Exception {		
		this.c = c;
		this.listaCidade = listaCidade;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaClienteInserir.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InserirCliente.fxml"));
		loader.setController(new JanelaClienteInserirController(c, proxId, listaCidade));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Inserir Cliente");
		stage.show();
	}

}
