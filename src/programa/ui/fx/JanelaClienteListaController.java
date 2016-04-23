package programa.ui.fx;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.TextFieldUtils.Mask;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class JanelaClienteListaController implements Initializable {
	@FXML
	private Button btFechar, btResetar, btInserir, btAlterar, btExcluir, btPesquisar, btLimpar;
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
	private TextField fCodigo, fNome, fCpf;
	@FXML
	private CheckBox chCodigo, chNome, chCpf, chCidade;
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

	public void initialize(URL url, ResourceBundle bundle) {

		for (int i = 0; i < c.size(); i++) {
			cliente.add(new ItensProperty(c.get(i).getCodCliente(), c.get(i).getNome(), c.get(i).getTel(),
					c.get(i).getCpf(), c.get(i).getCidade().getNome()));
		}

		tbCliente.setItems(cliente);
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
					uiCliente.excluirCliente(cli);
					cliente.clear();
					Stage stg = (Stage) btExcluir.getScene().getWindow();
					stg.close();
				}
			}
		});

		btLimpar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fCodigo.setText(null);
				fNome.setText(null);
				fCpf.setText(null);
				cbCidade.getSelectionModel().select(-1);

				chCodigo.setSelected(false);
				chNome.setSelected(false);
				chCpf.setSelected(false);
				chCidade.setSelected(false);
			}
		});

		btPesquisar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				boolean validacao = false;

				String nome = null;
				if (chNome.isSelected() && fNome.getText() != null) {
					nome = fNome.getText();
					fNome.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				} else {
					if (chNome.isSelected() && fNome.getText() == null) {
						fNome.setStyle(" -fx-control-inner-background: pink;");

					}
					if (chNome.isSelected() == false && fNome.getText() != null) {
						chNome.setStyle(" -fx-control-inner-background: pink;");

					}

				}
				if (chNome.isSelected() == false && fNome.getText() == null) {
					fNome.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				}

				long cod = 0;
				if (chCodigo.isSelected() && fCodigo.getText() != null) {
					cod = Long.valueOf(fCodigo.getText()).longValue();
					fCodigo.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				} else {
					if (chCodigo.isSelected() && fCodigo.getText() == null) {
						fCodigo.setStyle(" -fx-control-inner-background: pink;");

					}
					if (chCodigo.isSelected() == false && fCodigo.getText() != null) {
						chCodigo.setStyle(" -fx-control-inner-background: pink;");
					}

				}
				if (chCodigo.isSelected() == false && fCodigo.getText() == null) {
					fCodigo.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				}

				String cpf = null;
				if (chCpf.isSelected() && fCpf.getText() != null) {
					cpf = fNome.getText();
					fCpf.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				} else {
					if (chCpf.isSelected() && fCpf.getText() == null) {
						fCpf.setStyle(" -fx-control-inner-background: pink;");

					}
					if (chCpf.isSelected() == false && fNome.getText() != null) {
						chCpf.setStyle(" -fx-control-inner-background: pink;");

					}

				}
				if (chCpf.isSelected() == false && fCpf.getText() == null) {
					fCpf.setStyle(" -fx-control-inner-background: white;");
					validacao = true;
				}

				Cidade c = null;
				if (chCidade.isSelected() && cbCidade.getSelectionModel().getSelectedItem() != null) {

					String nmCidade = (String) cbCidade.getSelectionModel().getSelectedItem();
					if (!nmCidade.equals("QUALQUER CIDADE")) {
						for (int i = 0; i < listaCidade.size(); i++) {
							if (listaCidade.get(i).getNome() == nmCidade)
								c = listaCidade.get(i);

						}
					}
					validacao = true;

				}

				if (validacao == true) {
					if(nome == null && cpf == null && cod == 0 && c == null){
						cliente.clear();
						uiCliente.listarClientes();
						Stage stg = (Stage) btResetar.getScene().getWindow();
						stg.close();
					}else{
					Cliente clientePesq = Cliente.newInstance(nome, cpf, c);

					clientePesq.setCodCliente(cod);
					List<Cliente> cPesq = uiCliente.listarPesquisa(clientePesq);

					cliente.clear();

					try {
						JanelaClienteLista j = new JanelaClienteLista(cPesq, listaCidade, uiCliente);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stage stg = (Stage) btExcluir.getScene().getWindow();
					stg.close();
					}
				}
			}
		});

	}

}
