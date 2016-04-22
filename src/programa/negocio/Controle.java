package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioCidade;
import programa.dados.IRepositorioCliente;
import programa.dados.IRepositorioProduto;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Produto;

public class Controle {
	private ControladorCliente cCliente;
	private ControladorCidade cCidade;
	private ControladorProduto cProduto;

	public Controle(IRepositorioCliente repoCliente, IRepositorioCidade repoCidade, IRepositorioProduto repoProduto) {
		cCliente = new ControladorCliente(repoCliente);
		cCidade = new ControladorCidade(repoCidade);
		cProduto = new ControladorProduto(repoProduto);
	}

	public boolean inserir(Cliente c) {
		return cCliente.inserir(c);
	}

	public List<Cliente> getListaCliente() {
		return cCliente.getLista();
	}

	public long proxIdCliente() {
		return cCliente.getProxId();
	}


	public boolean alterar(Cliente c) {
		return cCliente.alterar(c);
	}

	public boolean excluir(Cliente c) {
		return cCliente.excluir(c);
		
	}

	public List<Cidade> getListaCidades() {
		return cCidade.getLista();
	}

	public Cidade getCidade(int cdCidade) {
		return cCidade.getCidade(cdCidade);
	}
	
	public List<Cliente> getPesquisa(Cliente c){
		return cCliente.getPesquisa(c);
	}

	public List<String> getListaEstado() {
		return cCidade.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return cCidade.getListaCest(sgEst);
	}

	public boolean inserir(Produto prod) {
		return cProduto.inserir(prod);
	}

	public long proxCod() {
		return cProduto.proxCod();
	}

	public boolean alterar(Produto prod) {
		return cProduto.alterar(prod);
	}

	public List<Produto> getListaProdutos() {
		return cProduto.getLista();
	}

	public List<Produto> getPesquisa(Produto prod) {
		return cProduto.getPesquisa(prod);
	}

	public boolean excluir(Produto p) {
		return cProduto.excluir(p);
	}
}
