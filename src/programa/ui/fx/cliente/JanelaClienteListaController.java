package programa.ui.fx.cliente;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jidefx.scene.control.field.FormattedTextField;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.util.FxUtil;
import programa.ui.fx.util.TextFieldUtils;
import programa.ui.fx.util.Validation;
import programa.ui.fx.util.FxUtil.AutoCompleteMode;
import programa.ui.fx.util.TextFieldUtils.Mask;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class JanelaClienteListaController implements Initializable {
	@FXML
	private Button btFechar, btResetar, btInserir, btAlterar, btExcluir, btLimpar;
	@FXML
	private TableView<ItensProperty> tbCliente;
	@FXML
	private TableColumn<ItensProperty, Integer> columnCodigo;
	@FXML
	private TableColumn<ItensProperty, String> columnNome;
	@FXML
	private TableColumn<ItensProperty, String> columnTel;
	@FXML
	private TableColumn<ItensProperty, String> columnCpf;
	@FXML
	private TableColumn<ItensProperty, String> columnCidade;
	@FXML
	private TextField fNome;
	@FXML
	private FormattedTextField fCpf, fCodigo;
	@FXML
	private ComboBox<String> cbCidade;
	@FXML
	private Stage stage;

	private UICliente uiCliente;
	private List<Cliente> c;
	private static ObservableList<ItensProperty> cliente = FXCollections.observableArrayList();
	private List<Cidade> listaCidade;

	public JanelaClienteListaController(UICliente uiCliente, List<Cliente> clientes, List<Cidade> listaCidade) {
		this.uiCliente = uiCliente;
		this.c = clientes;
		this.listaCidade = listaCidade;
	}

	public class ItensProperty {
		private SimpleIntegerProperty cod;
		private SimpleStringProperty nome;
		private SimpleStringProperty tel;
		private SimpleStringProperty cpf;
		private SimpleStringProperty cidade;

		public ItensProperty(long codigo, String nome, String tel, String cpf, String cidade) {

			this.cod = new SimpleIntegerProperty((int) codigo);
			this.nome = new SimpleStringProperty(nome);
			this.tel = new SimpleStringProperty(tel);
			this.cpf = new SimpleStringProperty(cpf);
			this.cidade = new SimpleStringProperty(cidade);
		}

		public int getCod() {
			return cod.get();
		}

		public void setCod(int cod) {
			this.cod.set(cod);
		}

		public String getNome() {
			return nome.get();
		}

		public void setNome(String nome) {
			this.nome.set(nome);
		}

		public String getTel() {
			return tel.get();
		}

		public void setTel(String tel) {
			this.tel.set(tel);
		}

		public String getCpf() {
			return cpf.get();
		}

		public void setCpf(String cpf) {
			this.cpf.set(cpf);
		}

		public String getCidade() {
			return cidade.get();
		}

		public void setCidade(String cidade) {
			this.cidade.set(cidade);
		}

	}

	private ObservableList<ItensProperty> findCliente() {
		ObservableList<ItensProperty> clienteEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : cliente) {
			if (itens.getNome().toLowerCase().contains(fNome.getText().toLowerCase()))
				clienteEncontrados.add(itens);
		}
		return clienteEncontrados;

	}
	
	private ObservableList<ItensProperty> findClienteCod() {
		ObservableList<ItensProperty> clienteEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : cliente) {
			if (itens.getCod() == Integer.parseInt(fCodigo.getText()))
				clienteEncontrados.add(itens);
		}
		return clienteEncontrados;
	}
	
	private ObservableList<ItensProperty> findClienteCidade() {
		ObservableList<ItensProperty> clienteEncontrados = FXCollections.observableArrayList();
		
		for (ItensProperty itens : cliente) {
			if (itens.getCidade().equals(cbCidade.getSelectionModel().getSelectedItem())){
				clienteEncontrados.add(itens);
			}
		}
		return clienteEncontrados;
	}
	
	private void reset(){
		for (int i = 0; i < c.size(); i++) {
			cliente.add(new ItensProperty(c.get(i).getCodCliente(), c.get(i).getNome(), c.get(i).getTel(),
					c.get(i).getCpf(), c.get(i).getCidade().getNome()));
		}

		tbCliente.setItems(cliente);
	}

	public void initialize(URL url, ResourceBundle bundle) {

		Validation.toCpfField(fCpf);
		Validation.toCodField(fCodigo);

		fNome.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.matches(".{0,45}") || newValue.isEmpty()) {
					fNome.setText(newValue);
				} else {
					fNome.setText(oldValue);
				}

			}
		});

		reset();

		columnCodigo.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));
		columnTel.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("tel"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("cpf"));
		columnCidade.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("cidade"));

		String[] nmcidades = new String[listaCidade.size() + 1];
		for (int i = 0; i < listaCidade.size(); i++) {
			nmcidades[i + 1] = listaCidade.get(i).getNome();
		}
		nmcidades[0] = "QUALQUER CIDADE";
		ObservableList<String> nmCid = FXCollections.observableArrayList(nmcidades);
		cbCidade.setItems(nmCid);
		cbCidade.getSelectionModel().select(0);

		TextFieldUtils.setMask(fCpf, Mask.MASK_CPF);

		TextFieldUtils.setMask(fCodigo, Mask.MASK_Inteiro);

		btFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cliente.clear();
				Stage stg = (Stage) btFechar.getScene().getWindow();
				stg.close();
			}
		});

		btResetar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cliente.clear();
				uiCliente.listarClientes();
				Stage stg = (Stage) btResetar.getScene().getWindow();
				stg.close();

			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCliente.lerCliente();
			}
		});

		btAlterar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cod = tbCliente.getSelectionModel().getSelectedItem().getCod();
				Cliente cli = null;
				for (int i = 0; i < c.size(); i++) {
					if (cod == c.get(i).getCodCliente())
						cli = c.get(i);
				}
				if (cli != null) {
					uiCliente.alterarCliente(cli);
					cliente.clear();
					Stage stg = (Stage) btAlterar.getScene().getWindow();
					stg.close();
				}
			}
		});

		btExcluir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cod = tbCliente.getSelectionModel().getSelectedItem().getCod();
				Cliente cli = null;
				for (int i = 0; i < c.size(); i++) {
					if (cod == c.get(i).getCodCliente())
						cli = c.get(i);
				}
				if (cli != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Excluir");
					alert.setHeaderText("Você vai excluir o cliente: ");
					alert.setContentText(
							"Código: " + cli.getCodCliente() + "\nNome: " + cli.getNome() + "\nTem certeza?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						uiCliente.excluirCliente(cli);
						Stage stg = (Stage) btExcluir.getScene().getWindow();
						cliente.clear();
						stg.close();
					} else {
						alert.close();
					}

				}
			}
		});

		btLimpar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fCodigo.setText("");
				fNome.setText("");
				fCpf.setText("");
				cbCidade.getSelectionModel().select(0);
				cliente.clear();
				reset();

			}
		});

		fNome.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fNome.getText().equals(""))
					tbCliente.setItems(findCliente());
				else
					tbCliente.setItems(cliente);
			}

		});
		
		fCodigo.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fCodigo.getText().equals(""))
					tbCliente.setItems(findClienteCod());
				else
					tbCliente.setItems(cliente);
			}
			
		});

		cbCidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!cbCidade.getSelectionModel().getSelectedItem().equals("QUALQUER CIDADE"))
					tbCliente.setItems(findClienteCidade());
				else
					tbCliente.setItems(cliente);
			}
		});
//		fCpf.setOnKeyReleased(new EventHandler<Event>() {
//
//			@Override
//			public void handle(Event event) {
//				if (!fCpf.getText().equals("..-"))
//					tbCliente.setItems(findClienteCpf());
//				else
//					tbCliente.setItems(cliente);
//			}
//		});

	
	}

}
