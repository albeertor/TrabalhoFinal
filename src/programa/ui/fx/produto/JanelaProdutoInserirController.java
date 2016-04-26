package programa.ui.fx.produto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.negocio.entidades.Produto;
import programa.ui.fx.util.TextFieldUtils;
import programa.ui.fx.util.TextFieldUtils.Mask;

public class JanelaProdutoInserirController implements Initializable {
	@FXML
	private Button btCancelar, btSalvar;
	@FXML
	private TextField fCod, fNome, fVlUnit, fQtd;
	@FXML
	private TextArea taDesc;

	private Produto p;
	private long proxCod;
	private UIProduto uiProduto;

	public JanelaProdutoInserirController(Produto p, long proxCod, UIProduto uiProduto) {
		this.p = p;
		this.proxCod = proxCod;
		this.uiProduto = uiProduto;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (p != null) {
			fCod.setText(p.getCod() + "");
			fNome.setText(p.getNome());
			fVlUnit.setText(p.getVlUnit() + "");
			fQtd.setText(p.getQtd() + "");
			taDesc.setText(p.getDesc());
		}else{
			fCod.setText(proxCod+"");
		}
		TextFieldUtils.setMask(fVlUnit, Mask.MASK_Double);
		
		TextFieldUtils.setMask(fQtd, Mask.MASK_Inteiro);
	
		btSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage) btCancelar.getScene().getWindow();

				boolean validacao = true;

				if (fNome.getText().equals("")) {
					fNome.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fNome.setStyle(" -fx-control-inner-background: white;");
				}

				if (fQtd.getText().equals("")) {
					fQtd.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fQtd.setStyle(" -fx-control-inner-background: white;");
				}

				if (fVlUnit.getText().equals("")) {
					fVlUnit.setStyle(" -fx-control-inner-background: pink;");
					validacao = false;
				} else {
					fVlUnit.setStyle(" -fx-control-inner-background: white;");
				}

				if (validacao == true && p == null) {
					String nome = fNome.getText();
					String desc = taDesc.getText();
					long cod = Long.valueOf(fCod.getText()).longValue();
					int qtd = Integer.parseInt(fQtd.getText());
					double vlUnit = Double.parseDouble(fVlUnit.getText());

					Produto prod = Produto.newInstance(nome, vlUnit, qtd, desc);
					prod.setCod(proxCod);
					uiProduto.inserirProduto(prod);

					stage.close();
				}
				if (validacao == true && p != null) {
					String nome = fNome.getText();
					String desc = taDesc.getText();
					long cod = Long.valueOf(fCod.getText()).longValue();
					int qtd = Integer.parseInt(fQtd.getText());
					double vlUnit = Double.parseDouble(fVlUnit.getText());

					Produto prod = Produto.newInstance(nome, vlUnit, qtd, desc);
					prod.setCod(p.getCod());
					uiProduto.alterar(prod);

					stage.close();
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
