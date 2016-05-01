package programa.ui.fx.cidade;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Produto;
import programa.ui.fx.JanelaPrincipal;
import programa.ui.fx.util.FxUtil;
import programa.ui.fx.util.TextFieldUtils;
import programa.ui.fx.util.FxUtil.AutoCompleteMode;
import programa.ui.fx.util.TextFieldUtils.Mask;

public class JanelaCidadeListaController implements Initializable {
	@FXML
	private TextField fCodigo, fNome;
	@FXML
	private ComboBox<String> cbSgEstado;
	@FXML
	private TableColumn<ItensProperty, Integer> columnCodigo;
	@FXML
	private TableColumn<ItensProperty, String> columnNome;
	@FXML
	private TableColumn<ItensProperty, String> columnSgEstado;
	@FXML
	private TableView<ItensProperty> tbCidade;
	@FXML
	private Button btFechar, btResetar, btInserir, btAlterar, btExcluir, btLimpar;

	private UICidade uiCidade;
	private List<Cidade> listaCidade;
	private List<String> estado;

	private static ObservableList<ItensProperty> city = FXCollections.observableArrayList();

	public JanelaCidadeListaController(UICidade uiCidade, List<Cidade> listaCidade) {
		this.uiCidade = uiCidade;
		this.listaCidade = listaCidade;
	}

	public class ItensProperty {
		private SimpleIntegerProperty cod;
		private SimpleStringProperty nome;
		private SimpleStringProperty sgEstado;

		public ItensProperty(long cod, String nome, String sgEstado) {
			this.cod = new SimpleIntegerProperty((int) cod);
			this.nome = new SimpleStringProperty(nome);
			this.sgEstado = new SimpleStringProperty(sgEstado);
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

		public String getSgEstado() {
			return sgEstado.get();
		}

		public void setSgEstado(String sgEstado) {
			this.sgEstado.set(sgEstado);
		}
	}

	private ObservableList<ItensProperty> findCidade() {
		ObservableList<ItensProperty> cidadeEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : city) {
			if (itens.getNome().contains(fNome.getText()))
				cidadeEncontrados.add(itens);
		}
		return cidadeEncontrados;

	}

	private ObservableList<ItensProperty> findCidadeCod() {
		ObservableList<ItensProperty> cidadeEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : city) {
			if (itens.getCod() == Integer.parseInt(fCodigo.getText()))
				cidadeEncontrados.add(itens);
		}
		return cidadeEncontrados;
	}

	private ObservableList<ItensProperty> findCidadeSg() {
		ObservableList<ItensProperty> cidadeEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : city) {
			if (itens.getSgEstado().equals(cbSgEstado.getSelectionModel().getSelectedItem())) {
				cidadeEncontrados.add(itens);
			}
		}
		return cidadeEncontrados;
	}

	private void reset() {
		for (int i = 0; i < listaCidade.size(); i++) {
			city.add(new ItensProperty(listaCidade.get(i).getCodCidade(), listaCidade.get(i).getNome(),
					listaCidade.get(i).getsgEstado()));
		}

		tbCidade.setItems(city);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reset();

		columnCodigo.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));
		columnSgEstado.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("sgEstado"));

		estado = uiCidade.getListaEstado();
		String[] est = new String[estado.size() + 1];
		for (int i = 0; i < estado.size(); i++) {
			est[i + 1] = estado.get(i);
		}
		est[0] = "QUALQUER ESTADO";
		ObservableList<String> estad = FXCollections.observableArrayList(est);
		cbSgEstado.setItems(estad);

		TextFieldUtils.setMask(fCodigo, Mask.MASK_Inteiro);

		fNome.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fNome.getText().equals(""))
					tbCidade.setItems(findCidade());
				else
					tbCidade.setItems(city);
			}

		});

		fCodigo.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fCodigo.getText().equals(""))
					tbCidade.setItems(findCidadeCod());
				else
					tbCidade.setItems(city);
			}

		});

		cbSgEstado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!cbSgEstado.getSelectionModel().getSelectedItem().equals("QUALQUER ESTADO"))
					tbCidade.setItems(findCidadeSg());
				else
					tbCidade.setItems(city);
			}
		});

		btFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				city.clear();
				Stage stg = (Stage) btFechar.getScene().getWindow();
				stg.close();
			}
		});

		btResetar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				city.clear();
				uiCidade.listarCidade();
				Stage stg = (Stage) btResetar.getScene().getWindow();
				stg.close();

			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiCidade.lerCidade();
			}
		});

		btAlterar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cod = tbCidade.getSelectionModel().getSelectedItem().getCod();
				Cidade cid = null;
				for (int i = 0; i < listaCidade.size(); i++) {
					if (cod == listaCidade.get(i).getCodCidade())
						cid = listaCidade.get(i);
				}
				if (cid != null) {

					city.clear();
					uiCidade.alterarCidade(cid);
					Stage stg = (Stage) btAlterar.getScene().getWindow();
					stg.close();
				}
			}
		});

		btExcluir.setOnAction(new EventHandler<ActionEvent>() {

			private Alert alert;

			@Override
			public void handle(ActionEvent event) {
				int cod = tbCidade.getSelectionModel().getSelectedItem().getCod();

				List<Cliente> listaCliente = JanelaPrincipal.getUiCliente().getListaCliente();

				boolean validate = true;
				for (int i = 0; i < listaCliente.size(); i++) {
					if (listaCliente.get(i).getCidade().getCodCidade() == cod)
						validate = false;
				}

				if (validate) {
					Cidade cid = null;
					for (int i = 0; i < listaCidade.size(); i++) {
						if (cod == listaCidade.get(i).getCodCidade())
							cid = listaCidade.get(i);
					}
					if (cid != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Excluir");
						alert.setHeaderText("Você vai excluir a cidade: ");
						alert.setContentText(
								"Código: " + cid.getCodCidade() + "\nNome: " + cid.getNome() + "\nTem certeza?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							uiCidade.excluir(cid);
							Stage stg = (Stage) btExcluir.getScene().getWindow();
							city.clear();
							stg.close();
						} else {
							alert.close();
						}
					}
					
				}else{
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Cidade");
					alert.setHeaderText("Não foi possivel excluir!");
					alert.setContentText("Cidade ligada a algum cliente!");
					alert.showAndWait();
				}
			}
		});

		btLimpar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fCodigo.setText("");
				fNome.setText("");
				cbSgEstado.getSelectionModel().select(0);
				city.clear();
				reset();

			}
		});

	}

}
