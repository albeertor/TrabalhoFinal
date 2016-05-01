package programa.dados;

import java.util.List;

import programa.negocio.entidades.Cliente;

public interface IRepositorioCliente {

	boolean inserir(Cliente c);

	boolean excluir(Cliente c);

	List<Cliente> getLista();

	Cliente buscaCpf(String cpf);

	long getProxId();

	boolean alterar(Cliente c);
	
//	List<Cliente> getPesquisa(Cliente c);


}