package programa.ui.fx.cliente;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

public class JanelaClienteLista extends Application {
	private static Stage stage;
	private UICliente uiCliente;
	private List<Cliente> clientes;
	private List<Cidade> listaCidade;

	public JanelaClienteLista(List<Cliente> clientes, List<Cidade> listaCidade, UICliente uiCliente) throws Exception {
		this.uiCliente = uiCliente;
		this.clientes = clientes;
		this.listaCidade = listaCidade;
		start(new Stage());
	}

	@Override
	public void start(Stage stage) throws Exception {
		JanelaClienteLista.stage = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListarCliente.fxml"));
		loader.setController(new JanelaClienteListaController(uiCliente, clientes, listaCidade));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Cliente");
	
		stage.show();
	}
}
