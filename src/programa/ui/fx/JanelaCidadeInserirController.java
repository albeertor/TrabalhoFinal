package programa.ui.fx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Produto;

public class JanelaCidadeInserirController implements Initializable{
	@FXML
	private TextField fCod, fNome;
	@FXML
	private ComboBox<String> cbSgEstado;
	@FXML
	private Button btSalvar, btCancelar;
	
	private long proxCod;
	private UICidade uiCidade;
	private Cidade c;
	private List<String> estado;

	public JanelaCidadeInserirController(Cidade c, long proxCod, UICidade uiCidade) {
		this.c = c;
		this.proxCod = proxCod;
		this.uiCidade = uiCidade;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		estado = uiCidade.getEstado();
		String[] est = new String[estado.size()];
		for (int i = 0; i < estado.size(); i++) {
			est[i] = estado.get(i);
		}
		ObservableList<String> estad = FXCollections.observableArrayList(est);
		cbSgEstado.setItems(estad);
		
		if (c != null) {
			fCod.setText(c.getCodCidade() + "");
			fNome.setText(c.getNome());
			cbSgEstado.getSelectionModel().select(c.getsgEstado());
			
		}else{
			fCod.setText(proxCod+"");
		}
		
		FxUtil.autoCompleteComboBox(cbSgEstado, FxUtil.AutoCompleteMode.STARTS_WITH);
		
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Stage stg = (Stage) btSalvar.getScene().getWindow();
				boolean validacao = true;
				
				if (fNome.getText().equals("")) {
					fNome.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fNome.setStyle(" -fx-control-inner-background: white;");
				}
				
				if (FxUtil.getComboBoxValue(cbSgEstado) == null) {
					cbSgEstado.setStyle(" -fx-background-color: pink;");
					validacao = false;
				} else {
					cbSgEstado.setStyle(" -fx-control-inner-background: white;");
					
				}
				
				if (validacao == true && c == null) {
					String nome = fNome.getText().toUpperCase();
					String sgEstado = FxUtil.getComboBoxValue(cbSgEstado);	

					Cidade city = Cidade.newInstance(nome, sgEstado);
					city.setCodCidade(proxCod);
					uiCidade.inserir(city);

					stg.close();
				}
				if (validacao == true && c != null) {
					String nome = fNome.getText().toUpperCase();
					String sgEstado = FxUtil.getComboBoxValue(cbSgEstado);	

					Cidade city = Cidade.newInstance(nome, sgEstado);
					city.setCodCidade(c.getCodCidade());
					uiCidade.alterar(city);

					stg.close();
				}
				
				
			}
		});
		
		btCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stg = (Stage) btCancelar.getScene().getWindow();
				stg.close();
			}
		});
	}

}
