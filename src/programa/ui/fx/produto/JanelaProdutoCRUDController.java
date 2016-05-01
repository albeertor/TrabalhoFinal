package programa.ui.fx.produto;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.negocio.entidades.Produto;
import programa.ui.fx.util.TextFieldUtils;
import programa.ui.fx.util.TextFieldUtils.Mask;

public class JanelaProdutoCRUDController implements Initializable{
	@FXML
	private Button btCancelar, btSalvar, btFechar, btResetar, btInserir, btAlterar, btExcluir, btLimpar;;
	@FXML
	private TextField fCod, fNomeInserir, fVlUnit, fCodigo, fNome;;
	@FXML
	private TextArea taDesc;
	@FXML
	private TableColumn<ItensProperty, Integer> columnCodigo;
	@FXML
	private TableColumn<ItensProperty, String> columnNome;
	@FXML
	private TableColumn<ItensProperty, Double> columnVlUnit;
	@FXML
	private TableView<ItensProperty> tbProduto;
	@FXML
	private Stage stage;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tabCadastro, tabListagem;
	
	private long proxCod;
	private UIProduto uiProduto;
	private List<Produto> produtos;
	private Produto p = null;
	private static ObservableList<ItensProperty> prods = FXCollections.observableArrayList();


	public JanelaProdutoCRUDController(long proxCod, UIProduto uiProduto, List<Produto> produtos) {
		this.proxCod = proxCod;
		this.uiProduto = uiProduto;
		this.produtos = produtos;
	}
	
	public class ItensProperty {
		private SimpleIntegerProperty cod;
		private SimpleStringProperty nome;
		private SimpleDoubleProperty vlUnit;

		public ItensProperty(long codigo, String nome,  Double vlUnit) {

			this.cod = new SimpleIntegerProperty((int) codigo);
			this.nome = new SimpleStringProperty(nome);		
			this.vlUnit = new SimpleDoubleProperty((Double) vlUnit);
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

		public Double getVlUnit() {
			return vlUnit.get();
		}

		public void setVlUnit(Double vlUnit) {
			this.vlUnit.set(vlUnit);
		}
	}
	
	private ObservableList<ItensProperty> findProdutosNome() {
		ObservableList<ItensProperty> produtoEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : prods) {
			if (itens.getNome().toLowerCase().contains(fNome.getText().toLowerCase()))
				produtoEncontrados.add(itens);
		}
		return produtoEncontrados;

	}
	
	private ObservableList<ItensProperty> findProdutosCod() {
		ObservableList<ItensProperty> produtoEncontrados = FXCollections.observableArrayList();

		for (ItensProperty itens : prods) {
			if (itens.getCod() == Integer.parseInt(fCodigo.getText()))
				produtoEncontrados.add(itens);
		}
		return produtoEncontrados;

	}
	
	private void reset(){
		produtos = uiProduto.getListaProdutos();
		for (int i = 0; i < produtos.size(); i++) {
			prods.add(new ItensProperty(produtos.get(i).getCod(), produtos.get(i).getNome(), produtos.get(i).getVlUnit()));
		}

		tbProduto.setItems(prods);
	}
	
	protected void alterar(Produto prod) {
		p = prod;
		fCod.setText(p.getCod() + "");
		fNomeInserir.setText(p.getNome());
		fVlUnit.setText(p.getVlUnit() + "");		
		taDesc.setText(p.getDesc());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//INSERIR
		if (p == null) {		
			fCod.setText(proxCod+"");
		}
		TextFieldUtils.setMask(fVlUnit, Mask.MASK_Double);
	
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();

				boolean validacao = true;

				if (fNomeInserir.getText().equals("")) {
					fNomeInserir.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fNomeInserir.setStyle(" -fx-control-inner-background: white;");
				}


				if (fVlUnit.getText().equals("")) {
					fVlUnit.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fVlUnit.setStyle(" -fx-control-inner-background: white;");
				}

				if (validacao == true && p == null) {
					String nome = fNomeInserir.getText();
					String desc = taDesc.getText();
					long cod = Long.valueOf(fCod.getText()).longValue();					
					double vlUnit = Double.parseDouble(fVlUnit.getText());

					Produto prod = Produto.newInstance(nome, vlUnit, desc);
					prod.setCod(proxCod);
					uiProduto.inserirProduto(prod);
					prods.clear();
					
					tabPane.getSelectionModel().select(tabListagem);
					reset();
				}
				if (validacao == true && p != null) {
					String nome = fNomeInserir.getText();
					String desc = taDesc.getText();
					long cod = Long.valueOf(fCod.getText()).longValue();
		
					double vlUnit = Double.parseDouble(fVlUnit.getText());

					Produto prod = Produto.newInstance(nome, vlUnit, desc);
					prod.setCod(p.getCod());
					uiProduto.alterar(prod);
					
					p = null;
					tabPane.getSelectionModel().select(tabListagem);
					prods.clear();
					reset();
				}

			}
		});

		btCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stg = (Stage) btCancelar.getScene().getWindow();
				prods.clear();
				reset();
				stg.close();
			}
		});
		
		//LISTAR
		reset();
		columnCodigo.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));		
		columnVlUnit.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("vlUnit"));
		
		TextFieldUtils.setMask(fCodigo, Mask.MASK_Inteiro);

		fNome.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fNome.getText().equals(""))
					tbProduto.setItems(findProdutosNome());
				else
					tbProduto.setItems(prods);
			}
			
		});
		
		fCodigo.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!fCodigo.getText().equals(""))
					tbProduto.setItems(findProdutosCod());
				else
					tbProduto.setItems(prods);
			}
			
		});
		

		
		btFechar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				prods.clear();
				Stage stg = (Stage) btFechar.getScene().getWindow();
				stg.close();
			}
		});

		btResetar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				prods.clear();
				reset();
			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tabPane.getSelectionModel().select(tabCadastro);
			}
		});
		
		btAlterar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cod = tbProduto.getSelectionModel().getSelectedItem().getCod();
				Produto prod = null;
				for (int i = 0; i < produtos.size(); i++) {
					if (cod == produtos.get(i).getCod())
						prod = produtos.get(i);
				}
				if (prod != null) {					
					prods.clear();
					tabPane.getSelectionModel().select(tabCadastro);
					alterar(prod);
				}
			}
		});

		btExcluir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cod = tbProduto.getSelectionModel().getSelectedItem().getCod();
				Produto prod = null;
				for (int i = 0; i < produtos.size(); i++) {
					if (cod == produtos.get(i).getCod())
						prod = produtos.get(i);
				}
				if (prod != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Excluir");
					alert.setHeaderText("Você vai excluir o produto: ");
					alert.setContentText("Código: " + prod.getCod() + "\nNome: " + prod.getNome() + "\nTem certeza?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){						
						uiProduto.excluir(prod);
						Stage stg = (Stage) btExcluir.getScene().getWindow();
						prods.clear();
						reset();
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
				prods.clear();
				reset();
			}
		});
		
	}



}
