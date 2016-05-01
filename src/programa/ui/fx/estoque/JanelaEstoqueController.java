package programa.ui.fx.estoque;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jidefx.scene.control.field.FormattedTextField;
import programa.negocio.entidades.Estoque;
import programa.negocio.entidades.Produto;
import programa.ui.fx.JanelaPrincipal;
import programa.ui.fx.produto.UIProduto;
import programa.ui.fx.util.TextFieldUtils;
import programa.ui.fx.util.Validation;
import programa.ui.fx.util.TextFieldUtils.Mask;

public class JanelaEstoqueController implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabCadastro, tabListagem;
	@FXML
	private AnchorPane paneList;
	@FXML
	private ComboBox<String> cbProduto;
	@FXML
	private TextField tfCodProduto, tfVlCompra, tfCodEstoqueCadastro;
	@FXML
	private DatePicker dtEnt, dtAdicao;
	@FXML
	private Button btAdd, btFechar, btAlterar, btCadastrar, btLimpar, btCancelar, btSalvar, btAlterarEstoque;
	@FXML
	private FormattedTextField tfEstoque, tfVlVenda, tfCod, tfNome;
	@FXML
	private TableView<ItensProperty> tbEstoque;
	@FXML
	private TableColumn<ItensProperty, Integer> columnCod;
	@FXML
	private TableColumn<ItensProperty, String> columnNome;
	@FXML
	private TableColumn<ItensProperty, Integer> columnEstoque;

	private List<Produto> produtos;
	private List<Estoque> estoque;
	private UIProduto uiProduto;
	private Alert alert;
	private UIEstoque uiEstoque;
	private long proxCod;
	private static ObservableList<ItensProperty> estoq = FXCollections.observableArrayList();
	private Estoque e;

	public JanelaEstoqueController(long proxCod, List<Estoque> estoque, UIEstoque uiEstoque) {
		this.proxCod = proxCod;
		this.estoque = estoque;
		this.uiEstoque = uiEstoque;
	}

	public class ItensProperty {
		private SimpleIntegerProperty cod;
		private SimpleStringProperty nome;
		private SimpleIntegerProperty estoque;

		public ItensProperty(long codigo, String nome, int estoque) {
			this.cod = new SimpleIntegerProperty((int) codigo);
			this.nome = new SimpleStringProperty(nome);
			this.estoque = new SimpleIntegerProperty(estoque);
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

		public int getEstoque() {
			return estoque.get();
		}

		public void setEstoque(int estoque) {
			this.estoque.set(estoque);
		}
	}

	private void cbProds() {
		produtos = uiProduto.getListaProdutos();
		String[] nmProd = new String[produtos.size()];
		for (int i = 0; i < produtos.size(); i++) {
			nmProd[i] = produtos.get(i).getNome();
		}
		ObservableList<String> prods = FXCollections.observableArrayList(nmProd);
		cbProduto.setItems(prods);
	}

	private void reset() {
		estoque = uiEstoque.getListaEstoque();
		ItensProperty item;
		for (int i = 0; i < estoque.size(); i++) {
			estoq.add(new ItensProperty(estoque.get(i).getCod(), estoque.get(i).getP().getNome(),
					estoque.get(i).getEstoque()));
		}
		tbEstoque.setItems(estoq);

	}

	private void alterar(Estoque est) {
		e = est;
		tfCodEstoqueCadastro.setText(e.getCod() + "");
		tfCodProduto.setText(e.getP().getCod() + "");
		tfVlCompra.setText(e.getP().getVlUnit() + "");
		tfEstoque.setText(e.getEstoque() + "");
		tfVlVenda.setText(e.getVlVenda() + "");

		Instant instant = Instant.ofEpochMilli(e.getDtEntrada().getTime());
		LocalDate dte = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

		dtEnt.setValue(dte);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		uiProduto = JanelaPrincipal.getUiProduto();

		tfCodEstoqueCadastro.setText(proxCod + "");

		// Cadastro

		Instant instant = Instant.ofEpochMilli(new Date().getTime());
		LocalDate dte = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		dtAdicao.setValue(dte);

		TextFieldUtils.setMask(tfVlVenda, Mask.MASK_Double);
		// tfEstoque.setSpinnerStyle(SpinnerStyle.INSIDE_RIGHT_VERTICAL);
		// tfEstoque.setSpinnersVisible(true);
		// tfAddEstoque.asSpinner(SpinnerStyle.INSIDE_RIGHT_VERTICAL);
		// tfAddEstoque.setText("2");
		// tfEstoque.setSpinnersVisible(true);

		cbProds();

		cbProduto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String prod = cbProduto.getSelectionModel().getSelectedItem();
				long cod = 0;
				Double vlrCompra = null;
				Double vlrVenda = null;
				int estoq = 0;
				Date dtent = null;
				Boolean validacao = false;

				for (int i = 0; i < produtos.size(); i++) {
					if (prod.equals(produtos.get(i).getNome())) {

						for (int j = 0; j < estoque.size(); j++) {
							if (prod.equals(estoque.get(j).getP().getNome())) {
								cod = produtos.get(i).getCod();
								vlrCompra = produtos.get(i).getVlUnit();
								estoq = estoque.get(j).getEstoque();
								vlrVenda = estoque.get(j).getVlVenda();
								dtent = estoque.get(j).getDtEntrada();
								Estoque e = Estoque.newInstance(produtos.get(i), estoque.get(j).getEstoque(),
										estoque.get(j).getVlVenda(), estoque.get(j).getDtEntrada());
								e.setCod(estoque.get(j).getCod());
								alterar(e);
								validacao = true;
							}
						}
					}

				}

				if (validacao == false) {
					tfVlVenda.setText("");
					tfEstoque.setText("");
					tfCodEstoqueCadastro.setText(proxCod + "");
					
					dtEnt.setValue(null);;
					for (int i = 0; i < produtos.size(); i++) {
						if (prod == produtos.get(i).getNome()) {
							cod = produtos.get(i).getCod();
							vlrCompra = produtos.get(i).getVlUnit();

							tfCodProduto.setText(cod + "");
							tfVlCompra.setText(vlrCompra + "");
							validacao = true;
						}
					}
				}

			}
		});

		btAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Produto");
				alert.setHeaderText("Inserir Produto");
				alert.setContentText("Ao inserir o produto, volte para a tela de estoque!");
				alert.showAndWait();
				uiProduto.lerProduto();
				Stage stg = (Stage) btAdd.getScene().getWindow();
				stg.close();

			}
		});
		
		btAlterarEstoque.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Alterar Estoque");
				dialog.setHeaderText("Estoque atual: " + tfEstoque.getText());
				dialog.setContentText("Estoque para adicionar ou remover:");
				
				ButtonType adicionar = new ButtonType("Adicionar");
				ButtonType remover = new ButtonType("Remover");
				ButtonType cancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().clear();
				dialog.getDialogPane().getButtonTypes().addAll(adicionar, remover, cancelar);
				
				Optional<String> result = dialog.showAndWait();
//				Optional<ButtonType> botao = dialog.;
				Optional<ButtonType> resulta = alert.showAndWait();


				if (result.isPresent()){
				    if(resulta.get() == adicionar){
				    	System.out.println("ADCIONAR " + result.get());
				    }else{
				    	System.out.println("REMOVER " + result.get() );
				    }
					
				}
	
			}
		});

		btSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();

				boolean validacao = true;
				
				if(cbProduto.getSelectionModel().getSelectedItem()  == null){
					cbProduto.setStyle(" -fx-background-color: pink;");
					validacao = false;
				}else{
					cbProduto.setStyle(" -fx-background-color: white;");
				}
				
				if(tfVlVenda.getText().equals("")){
					tfVlVenda.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				}else{
					tfVlVenda.setStyle(" -fx-control-inner-background: white;");
				}		

//
//				if (validacao == true && p == null) {
//					String nome = fNomeInserir.getText();
//					String desc = taDesc.getText();
//					long cod = Long.valueOf(fCod.getText()).longValue();					
//					double vlUnit = Double.parseDouble(fVlUnit.getText());
//
//					Produto prod = Produto.newInstance(nome, vlUnit, desc);
//					prod.setCod(proxCod);
//					uiProduto.inserirProduto(prod);
//					prods.clear();
//					
//					tabPane.getSelectionModel().select(tabListagem);
//					reset();
//				}
				
				if (e == null) {
					
				} else {

				}
			}
		});

		btCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stg = (Stage) btCancelar.getScene().getWindow();
				estoq.clear();
				reset();
				stg.close();
			}
		});

		// Listar
		reset();
		columnCod.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("estoque"));

		TextFieldUtils.setMask(tfCod, Mask.MASK_Inteiro);

		btLimpar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tfCod.setText("");
				tfNome.setText("");
				estoq.clear();
				reset();
			}
		});

	}

}
