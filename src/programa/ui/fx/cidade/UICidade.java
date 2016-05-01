package programa.ui.fx.cidade;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import programa.negocio.Controle;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Produto;


public class UICidade {
	private Controle ctr;
	private Alert alert;

	public UICidade(Controle controle) {
		this.ctr = controle;
	}	
	
	public List<Cidade> getListaCidade() {
		List<Cidade> cidades = ctr.getListaCidades();
		return cidades;		
	}
	
	public List<Cidade> getListaCidadeCod() {
		List<Cidade> cidades = ctr.getListaCidadesCod();
		return cidades;		
	}
	
	
	public void listarCidade(){
		List<Cidade> cid = getListaCidadeCod();
		try {
			JanelaCidadeLista j = new JanelaCidadeLista(cid, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	
	public List<Cidade> listarPesquisa(Cidade c) {
		return ctr.getPesquisa(c);
	}
*/

	public Cidade getCidade(int cdCidade) {
		return ctr.getCidade(cdCidade);
	}

	public List<String> getListaEstado() {
		return ctr.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return ctr.getListaCest(sgEst);
	}
	
	public long proxId(){
		return ctr.getProxId();
	}
	
	public void inserir(Cidade c){
		if (c != null) {
			if (ctr.inserir(c)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cidade");
				alert.setHeaderText(null);
				alert.setContentText("Inserido com sucesso!");
				alert.showAndWait();
			}
		}
	}
	
	public void alterarCidade(Cidade c){
		if (c != null) {
			JanelaCidadeInserir jc = null;
			try {
				jc = new JanelaCidadeInserir(c, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterar(Cidade c){
		if (ctr.alterar(c)) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cidade");
			alert.setHeaderText(null);
			alert.setContentText("Alterado com sucesso!");
			alert.showAndWait();
		}
	}
	
	public void excluir(Cidade c){
		if (c != null) {
			if (ctr.excluir(c)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cidade");
				alert.setHeaderText(null);
				alert.setContentText("Excluido com sucesso!");
				alert.showAndWait();
			}
		}
	}
	
	public List<String> getEstado(){
		return ctr.getEstado();
	}

	public void lerCidade() {
		long proxCod = ctr.getProxId();
		System.out.println(proxCod);
		JanelaCidadeInserir j = null;
		try {
			j = new JanelaCidadeInserir(proxCod,this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
