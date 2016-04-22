package programa.dados;

import java.util.List;

import programa.negocio.entidades.Cidade;

public interface IRepositorioCidade {
	List<Cidade> getLista();
	
	Cidade getCidade(int cdCidade);

	List<String> getListaEstado();

	List<Cidade> getListaCest(String sgEst);
}
