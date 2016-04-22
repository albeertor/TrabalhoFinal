package programa.ui.fx;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programa.Programa;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.swing.ClienteTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	private ComboBox<Cidade> cbCidade;
	@FXML
	private Stage stage;

	protected ClienteTableModel tmCliente;
	private UICliente uiCliente;
	private List<Cliente> c;
	private static ObservableList<ItensProperty> cliente = FXCollections.observableArrayList();;
	private List<Cidade> listCidade;


	public JanelaClienteListaController(UICliente uiCliente, List<Cliente> clientes, List<Cidade> listaCidade) {
		this.uiCliente = uiCliente;
		this.c = clientes;
		this.listCidade = listaCidade;
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
		System.out.println(cliente.get(2).getNome());

		columnCodigo.setCellValueFactory(new PropertyValueFactory<ItensProperty, Integer>("cod"));
		columnNome.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("nome"));
		columnTel.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("tel"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("cpf"));
		columnCidade.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("cidade"));

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
				Programa.uiCliente.listarClientes();
				Stage stg = (Stage) btResetar.getScene().getWindow();
				stg.close();

			}
		});

		btInserir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Programa.uiCliente.lerCliente();
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
				if(cli != null)
					Programa.uiCliente.alterarCliente(cli);
				
			}
		});

		btExcluir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		btPesquisar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		btLimpar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		// btPesquisar.addActionListener(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent arg0) {
		// boolean validacao = true;
		// String dataMasc =" / / ";
		// Date dataNasc = null;
		//
		// if(chCodigo.isSelected() && fCodigo.getText().isEmpty()){
		// fCodigo.setBackground(Color.pink);
		// validacao = false;
		// }else
		// fCodigo.setBackground(Color.WHITE);
		//
		// if(chNome.isSelected() && fNome.getText().isEmpty()){
		// fNome.setBackground(Color.pink);
		// validacao = false;
		// }else
		// fNome.setBackground(Color.WHITE);
		//
		// if(chCpf.isSelected() && fCpf.getText().isEmpty()){
		//
		//
		// }
		//
		// if (validacao == true) {
		// long cod = 0;
		// if (chCodigo.isSelected()) {
		// if (fCodigo.getText().trim().length() > 0) {
		// try {
		// cod =
		// NumberFormat.getIntegerInstance().parse(fCodigo.getText()).longValue();
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		//
		// String nome = null;
		// if (chNome.isSelected()) {
		// if (fNome.getText() != null) {
		// nome = fNome.getText();
		// }
		// }
		//
		// if (chDtAniv.isSelected()) {
		// if (!fDtAniv.getText().equals(dataMasc)) {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// String ds = fDtAniv.getText();
		// dataNasc = null;
		// try {
		// dataNasc = sdf.parse(ds);
		// } catch (ParseException e1) {
		// e1.printStackTrace();
		// }
		// } else
		// dataNasc = null;
		// }
		//
		// Cidade c = null;
		// if (chCidade.isSelected()) {
		// String nmCidade = (String) cbCidade.getSelectedItem();
		// if (!cbCidade.getSelectedItem().equals("QUALQUER UMA")) {
		// for (int i = 0; i < lista.size(); i++) {
		// if (lista.get(i).getNome() == nmCidade)
		// c = lista.get(i);
		// }
		// }
		// }
		//
		// clientePesq = Cliente.newInstance(nome, dataNasc, c);
		//
		// clientePesq.setCodCliente(cod);
		// List<Cliente> cPesq = uiCliente.listarPesquisa(clientePesq);
		//
		// frame.dispose();
		// initGUI(cPesq, lista);
		// frame.setVisible(true);
		// }else
		// JOptionPane.showMessageDialog(frame, "Preencha corretamente os campos
		// selecionados", "Erro",
		// JOptionPane.ERROR_MESSAGE);
		//
		// }
		// });
		//
		//
		// btLimpar.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// fCodigo.setText(null);
		// fNome.setText(null);
		// fDtAniv.setText(null);
		// cbCidade.setSelectedIndex(0);
		// }
		// });
	}

}
