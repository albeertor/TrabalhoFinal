package programa.ui.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Produto;
import programa.ui.fx.TextFieldUtils.Mask;

public class JanelaProdutoListaController implements Initializable {
	@FXML
	private TextField fCodigo, fNome;
	@FXML
	private TableColumn<ItensProperty, Integer> columnCodigo;
	@FXML
	private TableColumn<ItensProperty, String> columnNome;
	@FXML
	private TableColumn<ItensProperty, Integer> columnQtd;
	@FXML
	private TableColumn<ItensProperty, Double> columnVlUnit;
	@FXML
	private Button btFechar, btResetar, btInserir, btAlterar, btExcluir, btPesquisar, btLimpar;
	@FXML
	private TableView<ItensProperty> tbProduto;
	@FXML
	private CheckBox chCodigo, chNome;
	@FXML
	private Stage stage;

	private List<Produto> produtos;
	private UIProduto uiProduto;
	private static ObservableList<ItensProperty> prods = FXCollections.observableArrayList();

	public JanelaProdutoListaController(List<Produto> produtos, UIProduto uiProduto) {
		this.produtos = produtos;
		this.uiProduto = uiProduto;
	}

	public class ItensProperty {
		private SimpleIntegerProperty cod;
		private SimpleStringProperty nome;
		private SimpleIntegerProperty qtd;
		private SimpleDoubleProperty vlUnit;

		public ItensProperty(long codigo, String nome, int qtd, Double vlUnit) {

			this.cod = new SimpleIntegerProperty((int) codigo);
			this.nome = new SimpleStringProperty(nome);
			this.qtd = new SimpleIntegerProperty((int) qtd);
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

		public int getQtd() {
			return qtd.get();
		}

		public void setQtd(int qtd) {
			this.qtd.set(qtd);
		}

		public Double getVlUnit() {
			return vlUnit.get();
		}

		public void setVlUnit(Double vlUnit) {
			this.vlUnit.set(vlUnit);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < produtos.size(); i++) {
			prods.add(new ItensProperty(produtos.get(i).getCod(), produtos.get(i).getNome(), produtos.get(i).getQtd(), produtos.get(i).getVlUnit()));
		}

		tbProduto.setItems(prods);
		columnCodigo.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));
		columnQtd.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("qtd"));
		columnVlUnit.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("vlUnit"));
		
		TextFieldUtils.setMask(fCodigo, Mask.MASK_Inteiro);
	
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
				uiProduto.listarProdutos();
				Stage stg = (Stage) btResetar.getScene().getWindow();
				stg.close();

			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiProduto.lerProduto();
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
					uiProduto.alterarProduto(prod);
					Stage stg = (Stage) btAlterar.getScene().getWindow();
					stg.close();
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
					
					prods.clear();
					uiProduto.excluir(prod);
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
				
				chCodigo.setSelected(false);
				chNome.setSelected(false);
				
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
				
				if (validacao == true) {
					
					Produto prodPesq = Produto.newInstance(nome);

					prodPesq.setCod(cod);
					List<Produto> pesq = uiProduto.listarPesquisa(prodPesq);

					prods.clear();

					try {
						JanelaProdutoLista j = new JanelaProdutoLista(pesq, uiProduto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stage stg = (Stage) btExcluir.getScene().getWindow();
					stg.close();
					}
			}
		});

	}

}
