package programa;

import programa.dados.CidadeDAO;
import programa.dados.ClienteDAO;
import programa.dados.IRepositorioCidade;
import programa.dados.IRepositorioCliente;
import programa.dados.IRepositorioProduto;
import programa.dados.ProdutoDAO;
import programa.negocio.Controle;
import programa.ui.fx.UICidade;
import programa.ui.fx.UICliente;
import programa.ui.fx.UIProduto;
import programa.ui.fx.JanelaPrincipal;
//import programa.ui.swing.UICidade;
//import programa.ui.swing.UICliente;
//import programa.ui.swing.JanelaPrincipal;

public class Programa {
	public static UICliente uiCliente;
	public static UICidade uiCidade;
	public static UIProduto uiProduto;
	public static void main(String[] args) {

		IRepositorioCliente repoCliente = new ClienteDAO();
		IRepositorioCidade repoCidade = new CidadeDAO();
		IRepositorioProduto repoProduto = new ProdutoDAO();
	
		Controle ctr = new Controle(repoCliente,repoCidade,repoProduto);
		
		uiCliente = new UICliente(ctr);
		uiCidade = new UICidade(ctr);
		uiProduto = new UIProduto(ctr);
		
		JanelaPrincipal j = new JanelaPrincipal(uiCliente,uiCidade,uiProduto);
	}
	

}
