package programa.ui.fx;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import programa.Programa;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	public JanelaClienteInserirController(Cliente c, long proxId, List<Cidade> listaCidade) {
		this.c = c;
		this.proxId = proxId;
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
		         }	}
			
			@Override
			public LocalDate fromString(String string) {
				 if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
			}
		});
		
		
		dtNasc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LocalDate date = dtNasc.getValue();
			    System.out.println("Selected date: " + date);
			}
		});
		
		if(c != null){
			fCod.setText(c.getCodCliente()+"");
			fNome.setText(c.getNome());
			fTel.setText(c.getTel());
			fCPF.setText(c.getCpf());
			fCEP.setText(c.getCep());
			fRG.setText(c.getRg());
			Date input = c.getDtNasc();
			Instant instant = input.toInstant();
			ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
			LocalDate dte = zdt.toLocalDate();
			dtNasc.setValue(dte);			
			fEndereco.setText(c.getEndereco());
				
			estado = Programa.uiCidade.getListaEstado();
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
				
		}else{
			fCod.setText(proxId + "");
				
			estado = Programa.uiCidade.getListaEstado();
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
		
		cbSgEstado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String sgEst = cbSgEstado.getSelectionModel().getSelectedItem();
				List<Cidade> city = Programa.uiCidade.getListaCest(sgEst);
				String[] nmcidades = new String[city.size()];
				for (int i = 0; i < city.size(); i++) {
					nmcidades[i] = city.get(i).getNome();
				}
				ObservableList<String> nmCid = FXCollections.observableArrayList(nmcidades);
				cbCidade.setItems(nmCid);
				cbCidade.setDisable(false);
			}
		});;
		
		
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();
				
				
					boolean validacao = true;
					if (fNome.getText().equals("")) {
						fNome.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
					} else
						fNome.setStyle(" -fx-control-inner-background: whit;");
					
					LocalDate dt = dtNasc.getValue();
					Instant instant = Instant.from(dt.atStartOfDay(ZoneId.systemDefault()));
					Date date = Date.from(instant);
					if(date.getTime() < new Date().getTime()){
						System.out.println("ANTES DE HJ");
					}else{
						validacao = false;
					}
					
					if (fRG.getText().equals("")) {
						fRG.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
					} else
						fRG.setStyle(" -fx-control-inner-background: white;");

					if (!validarCPF.validarCpf(fCPF.getText().replaceAll("\\D",""))) {
						fCPF.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
						JOptionPane.showMessageDialog(null, "CPF inválido", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else
						fCPF.setStyle(" -fx-control-inner-background: white;");

					if (fCEP.getText().equals("     -   ")) {
						fCEP.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
					} else
						fCEP.setStyle(" -fx-control-inner-background: WHITE;");

					if (fEndereco.getText().equals("")) {
						fEndereco.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
					} else
						fEndereco.setStyle(" -fx-control-inner-background: white;");

					if (fTel.getText().equals("(  )     -    ")) {
						fTel.setStyle(" -fx-control-inner-background: pink;");
						validacao = false;
					} else
						fTel.setStyle(" -fx-control-inner-background: white;");
					
					if (validacao == true && c == null) {
						String nome = fNome.getText();
											
						String cpf = fCPF.getText();
						String rg = fRG.getText();
						String tel = fTel.getText();
						String cep = fCEP.getText();
						String endereco = fEndereco.getText();
	
						Cidade c = null;
						String nmCidade = (String) cbCidade.getSelectionModel().getSelectedItem();
						for (int i = 0; i < listaCidade.size(); i++) {
							if (listaCidade.get(i).getNome() == nmCidade)
								c = listaCidade.get(i);
						}
				
						Cliente cliente = Cliente.newInstance(nome, date, tel, cpf, rg, cep, endereco, c);
						cliente.setCodCliente(proxId);
						Programa.uiCliente.inserirCliente(cliente);
						stage.close();
					}else{
						if(validacao == true && c != null){
							String nome = fNome.getText();
							
							String cpf = fCPF.getText();
							String rg = fRG.getText();
							String tel = fTel.getText();
							String cep = fCEP.getText();
							String endereco = fEndereco.getText();
		
							Cidade cid = null;
							String nmCidade = (String) cbCidade.getSelectionModel().getSelectedItem();
							for (int i = 0; i < listaCidade.size(); i++) {
								if (listaCidade.get(i).getNome() == nmCidade)
									cid = listaCidade.get(i);
							}
							Cliente cliente = Cliente.newInstance(nome, date, tel, cpf, rg, cep, endereco, cid);
							cliente.setCodCliente(c.getCodCliente());
							Programa.uiCliente.alterar(cliente);
						}else{
						JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos corretamentes", "Erro",
								JOptionPane.ERROR_MESSAGE);
						}
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
