package programa.dados;

import java.util.List;

import programa.negocio.entidades.Estoque;
import programa.negocio.entidades.Produto;

public interface IRepositorioEstoque {

	List<Estoque> getLista();

	Produto getProduto(long cdProduto);

	long proxCodEstoque();

}
