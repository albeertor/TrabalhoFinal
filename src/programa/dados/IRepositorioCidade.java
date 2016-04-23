package programa.dados;

import java.util.List;

import programa.negocio.entidades.Cidade;

public interface IRepositorioCidade {
	long getProxId();

	List<Cidade> getLista();
	
	Cidade getCidade(int cdCidade);

	List<String> getListaEstado();

	List<Cidade> getListaCest(String sgEst);

	boolean inserir(Cidade c);
	
	boolean alterar(Cidade c);
	
	boolean excluir(Cidade c);

	List<String> getEstado();

	List<Cidade> getPesquisa(Cidade c);
	
	
}
