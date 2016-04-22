package programa.dados;

import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Cliente;

public class RepositorioClienteList implements IRepositorioCliente {
	private List<Cliente> clientes;
	private long proxId;

	public RepositorioClienteList() {
		clientes = new ArrayList<Cliente>();
		proxId = 1;
	}
	public Cliente buscaCpf(String cpf) {
		for (int i = 0; i < clientes.size(); i++) {
			if (cpf.equals(clientes.get(i).getCpf()))
				return clientes.get(i);
		}
		return null;	
	}

	public boolean inserir(Cliente c) {
		if (c != null) {
			c.setCodCliente(proxId);
			proxId++;
			return clientes.add(c);
		} else
			return false;
	}

	public List<Cliente> getLista() {
		return new ArrayList<Cliente>(clientes);
	}

	public long getProxId() {
		return proxId;
	}
	
	@Override
	public boolean excluir(Cliente c) {
		return clientes.remove(c);
	}
	@Override
	public boolean alterar(Cliente c) {
		int cod = (int) c.getCodCliente();
		clientes.set(cod - 1, c);
		return true;
	}
	
	@Override
	public List<Cliente> getPesquisa(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

}
