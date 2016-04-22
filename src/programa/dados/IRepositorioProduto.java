package programa.dados;

import java.util.List;

import programa.negocio.entidades.Produto;

public interface IRepositorioProduto {

	boolean inserir(Produto prod);

	long proxCod();

	boolean alterar(Produto prod);
	
	boolean excluir(Produto prod);

	List<Produto> getLista();

	List<Produto> getPesquisa(Produto prod);

}
