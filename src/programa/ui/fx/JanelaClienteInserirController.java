package programa.ui.fx;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.TextFieldUtils.Mask;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class JanelaClienteInserirController implements Initializable {
	@FXML
	private Button btCancelar, btSalvar;
	@FXML
	private TextField fCod, fNome, fTel, fCPF, fCEP, fRG, fEndereco;
	@FXML
	private DatePicker dtNasc;
	@FXML
	private ComboBox<String> cbCidade;
	@FXML
	private ComboBox<String> cbSgEstado;

	private Cliente c;
	private long proxId;
	private List<Cidade> listaCidade;
	private List<String> estado;
	private UICliente uiCliente;

	public JanelaClienteInserirController(Cliente c, long proxId, List<Cidade> listaCidade, UICliente uiCLiente) {
		this.c = c;
		this.proxId = proxId;
		this.uiCliente = uiCLiente;
		this.listaCidade = listaCidade;
	}

	public void initialize(URL url, ResourceBundle bundle) {
		dtNasc.setShowWeekNumbers(false);

		String pattern = "dd/MM/yyyy";

		dtNasc.setPromptText(pattern.toLowerCase());

		dtNasc.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		});
		
		FxUtil.autoCompleteComboBox(cbSgEstado, FxUtil.AutoCompleteMode.STARTS_WITH);
		
		FxUtil.autoCompleteComboBox(cbCidade, FxUtil.AutoCompleteMode.STARTS_WITH);

		if (c != null) {
			fCod.setText(c.getCodCliente() + "");
			fNome.setText(c.getNome());
			fTel.setText(c.getTel());
			fCPF.setText(c.getCpf());
			fCEP.setText(c.getCep());
			fRG.setText(c.getRg());
			fEndereco.setText(c.getEndereco());

			Instant instant = Instant.ofEpochMilli(c.getDtNasc().getTime());
			LocalDate dte = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

			dtNasc.setValue(dte);

			estado = JanelaPrincipal.getUiCidade().getListaEstado();
			String[] est = new String[estado.size()];
			for (int i = 0; i < estado.size(); i++) {
				est[i] = estado.get(i);
			}
			ObservableList<String> estad = FXCollections.observableArrayList(est);
			cbSgEstado.setItems(estad);

			String[] nmcidades = new String[listaCidade.size()];
			for (int i = 0; i < listaCidade.size(); i++) {
				nmcidades[i] = listaCidade.get(i).getNome();
			}
			ObservableList<String> nmCid = FXCollections.observableArrayList(nmcidades);
			cbCidade.setItems(nmCid);
			cbCidade.setDisable(true);

			cbSgEstado.getSelectionModel().select(c.getCidade().getsgEstado());
			cbCidade.getSelectionModel().select(c.getCidade().getNome());

		} else {
			fCod.setText(proxId + "");

			estado = JanelaPrincipal.getUiCidade().getListaEstado();
			String[] est = new String[estado.size()];
			for (int i = 0; i < estado.size(); i++) {
				est[i] = estado.get(i);
			}
			ObservableList<String> estad = FXCollections.observableArrayList(est);
			cbSgEstado.setItems(estad);
			cbCidade.setDisable(true);

			String[] nmcidades = new String[listaCidade.size()];
			for (int i = 0; i < listaCidade.size(); i++) {
				nmcidades[i] = listaCidade.get(i).getNome();
			}
			ObservableList<String> nmCid = FXCollections.observableArrayList(nmcidades);
			cbCidade.setItems(nmCid);

		}
		TextFieldUtils.setMask(fTel, Mask.MASK_TELEFONE);
				
		TextFieldUtils.setMask(fCPF, Mask.MASK_CPF);
		
		TextFieldUtils.setMask(fCEP, Mask.MASK_CEP);
				
		TextFieldUtils.setMask(fRG, Mask.MASK_Inteiro);
		 
		cbSgEstado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String sgEst = FxUtil.getComboBoxValue(cbSgEstado);
				List<Cidade> city = JanelaPrincipal.getUiCidade().getListaCest(sgEst);
				String[] nmcidades = new String[city.size()];
				for (int i = 0; i < city.size(); i++) {
					nmcidades[i] = city.get(i).getNome();
				}
				ObservableList<String> nmCid = FXCollections.observableArrayList(nmcidades);
				cbCidade.setItems(nmCid);
				cbCidade.setDisable(false);
			}
		});
		;

		btSalvar.setOnAction(new EventHandler<ActionEvent>() {

			private LocalDate dt;
			private Date date;
			private Alert alert = new Alert(AlertType.ERROR);
			private String errorDt = "", errorCpf = "", errorSg = "", errorCidade = "";

			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();

				boolean validacao = true;

				if (!validarCPF.validarCpf(fCPF.getText().replaceAll("\\D", ""))) {
					fCPF.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
					errorCpf = ("CPF inválida!\n");
				} else {
					errorCpf = ("\n");
				}

				if (dtNasc.getValue() != null) {
					dt = dtNasc.getValue();
					Instant instant = Instant.from(dt.atStartOfDay(ZoneId.systemDefault()));
					date = Date.from(instant);
					if (date.getTime() > new Date().getTime()) {
						validacao = false;
						dtNasc.setStyle(" -fx-control-inner-background: pink;");
						errorDt = ("Data inválida!\n");
					} else {
						if (date.getTime() < new Date().getTime()) {
							dtNasc.setStyle(" -fx-control-inner-background: white;");
							errorDt = ("\n");
						}
					}
				} else {
					validacao = false;
					dtNasc.setStyle(" -fx-control-inner-background: pink;");
					errorDt = ("Insira uma data!\n");
				}

				if (fNome.getText().equals("")) {
					fNome.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fNome.setStyle(" -fx-control-inner-background: white;");
				}

				if (fRG.getText().equals("")) {
					fRG.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fRG.setStyle(" -fx-control-inner-background: white;");
				}

				if (fCEP.getText().equals("     -   ") || fCEP.getText().equals("")) {
					fCEP.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fCEP.setStyle(" -fx-control-inner-background: white;");
				}

				if (fEndereco.getText().equals("")) {
					fEndereco.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fEndereco.setStyle(" -fx-control-inner-background: white;");
				}

				if (fTel.getText().equals("(  )     -    ") || fTel.getText().equals("")) {
					fTel.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fTel.setStyle(" -fx-control-inner-background: white;");
				}
				
				if (FxUtil.getComboBoxValue(cbSgEstado) == null) {
					cbSgEstado.setStyle(" -fx-background-color: pink;");
					validacao = false;
					errorSg = ("Selecione um estado!\n");
				} else {
					cbSgEstado.setStyle(" -fx-control-inner-background: white;");
					errorSg = ("\n");
				}

				if (FxUtil.getComboBoxValue(cbCidade) == null) {
					cbCidade.setStyle(" -fx-background-color: pink;");
					validacao = false;
					errorCidade = ("Selecione uma cidade!\n");
				} else {
					cbCidade.setStyle(" -fx-control-inner-background: white;");
					errorCidade = ("\n");
				}

				if (validacao == true && c == null) {
					String nome = fNome.getText();
					String cpf = fCPF.getText();
					String rg = fRG.getText();
					String tel = fTel.getText();
					String cep = fCEP.getText();
					String endereco = fEndereco.getText();

					Cidade c = null;
					String nmCidade = FxUtil.getComboBoxValue(cbCidade);

					for (int i = 0; i < listaCidade.size(); i++) {
						if (listaCidade.get(i).getNome().equals(nmCidade)) {
							c = listaCidade.get(i);
						}
					}

					Cliente cliente = Cliente.newInstance(nome, date, tel, cpf, rg, cep, endereco, c);
					cliente.setCodCliente(proxId);
					uiCliente.inserirCliente(cliente);
					stage.close();
				}
				if (validacao == true && c != null) {
					String nome = fNome.getText();
					String cpf = fCPF.getText();
					String rg = fRG.getText();
					String tel = fTel.getText();
					String cep = fCEP.getText();
					String endereco = fEndereco.getText();

					Cidade cid = null;
					String nmCidade = (String) FxUtil.getComboBoxValue(cbCidade);
					for (int i = 0; i < listaCidade.size(); i++) {
						if (listaCidade.get(i).getNome() == nmCidade) {
							cid = listaCidade.get(i);
						}
					}

					Cliente cliente = Cliente.newInstance(nome, date, tel, cpf, rg, cep, endereco, cid);
					cliente.setCodCliente(c.getCodCliente());
					uiCliente.alterar(cliente);
					stage.close();
				}
				if (validacao == false) {
					alert.setTitle("Erro ao Salvar");
					alert.setHeaderText("Preencha todos os campos corretamente!");
					alert.setContentText(errorDt + errorCpf + errorSg + errorCidade);

					alert.showAndWait();
				}
			}
		});

		btCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();
				stage.close();
			}
		});

	}
}
