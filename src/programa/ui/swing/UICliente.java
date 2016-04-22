package programa.ui.swing;

import java.util.List;

import javax.swing.JOptionPane;

import programa.Programa;
import programa.negocio.Controle;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.swing.JanelaClienteAlterar;
import programa.ui.swing.JanelaClienteExcluir;
import programa.ui.swing.JanelaClienteInserir;
import programa.ui.swing.JanelaClienteLista;

public class UICliente {
	private Controle ctr;
			
	public List<Cidade> getListaCidade(){
		return ctr.getListaCidades();				
	}
	
	public List<Cliente> getListaCliente(){
		return ctr.getListaCliente();
	}

	public UICliente(Controle controle) {
		this.ctr = controle;
	}
	
	public void inserirCliente() {
		Cliente c = lerCliente();
		if (c != null) {
			if (ctr.inserir(c))
				JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso");
			else
				JOptionPane.showMessageDialog(null, "Falha ao inserir cliente");
		}
	}

	public Cliente lerCliente() {
		long proxId = ctr.proxIdCliente();	
		
		JanelaClienteInserir j = new JanelaClienteInserir(proxId,getListaCidade());
		Cliente a = j.getCliente();
		return a;
	}

	public void listarClientes() {
		List<Cliente> clientes = ctr.getListaCliente();
		JanelaClienteLista j = new JanelaClienteLista(clientes,getListaCidade(),this);	
	}	
		
	public List<Cliente> listarPesquisa(Cliente c){
		return ctr.getPesquisa(c);		
	}
	

	public void alterarCliente() {
		List<Cliente> clientes = ctr.getListaCliente();
		JanelaClienteAlterar j = new JanelaClienteAlterar(clientes,getListaCidade(),this);
		
		Cliente c = j.getSelectedCliente();	
		if(c != null){
		JanelaClienteInserir jc = new JanelaClienteInserir(c,getListaCidade());
		c = jc.getCliente();
		
		if(ctr.alterar(c))
			JOptionPane.showMessageDialog(null, "Cliente Alterado");
		else
			JOptionPane.showMessageDialog(null, "Falha ao alterar cliente");
		}
	}

	public void excluirCliente() {
		List<Cliente> clientes = ctr.getListaCliente();
		JanelaClienteExcluir j = new JanelaClienteExcluir(clientes,getListaCidade(),this);
		Cliente c = j.getSelectedCliente();
		if(c != null){
		if (ctr.excluir(c))
			JOptionPane.showMessageDialog(null, "Cliente excluido");
		}
	}
}
