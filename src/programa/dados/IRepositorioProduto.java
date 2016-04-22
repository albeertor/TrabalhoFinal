package programa.dados;

import programa.negocio.entidades.Produto;

public interface IRepositorioProduto {

	boolean inserir(Produto prod);

	long proxCod();

}
