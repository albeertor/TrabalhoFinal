package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioCliente;
import programa.negocio.entidades.Cliente;


public class ControladorCliente {
	private IRepositorioCliente repoCliente;
	
	public ControladorCliente(IRepositorioCliente repoCliente) {
		this.repoCliente = repoCliente;
	}
	
	public boolean inserir(Cliente c) {
		Cliente aux = repoCliente.buscaCpf(c.getCpf());
		if (aux == null)
			return repoCliente.inserir(c);
		else
			return false;
	}

	public List<Cliente> getLista() {
		return repoCliente.getLista();
	}
	
	public long getProxId(){
		return repoCliente.getProxId();
	}

	public boolean alterar(Cliente c) {
		return repoCliente.alterar(c);
	}
	
	public boolean excluir(Cliente c) {
		return repoCliente.excluir(c);		
	}

	/*public List<Cliente> getPesquisa(Cliente c) {
		return repoCliente.getPesquisa(c);
	}*/
}
