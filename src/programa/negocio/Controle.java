package programa.negocio;

import java.util.List;

import programa.dados.IRepositorioCidade;
import programa.dados.IRepositorioCliente;
import programa.dados.IRepositorioEstoque;
import programa.dados.IRepositorioProduto;
import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.negocio.entidades.Estoque;
import programa.negocio.entidades.Produto;

public class Controle {
	private ControladorCliente cCliente;
	private ControladorCidade cCidade;
	private ControladorProduto cProduto;
	private ControladorEstoque cEstoque;

	public Controle(IRepositorioCliente repoCliente, IRepositorioCidade repoCidade, IRepositorioProduto repoProduto, IRepositorioEstoque repoEstoque) {
		cCliente = new ControladorCliente(repoCliente);
		cCidade = new ControladorCidade(repoCidade);
		cProduto = new ControladorProduto(repoProduto);
		cEstoque = new ControladorEstoque(repoEstoque);
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

	/*
	 * public List<Cliente> getPesquisa(Cliente c){ return
	 * cCliente.getPesquisa(c); }
	 */
	public List<String> getListaEstado() {
		return cCidade.getListaEstado();
	}

	public List<Cidade> getListaCest(String sgEst) {
		return cCidade.getListaCest(sgEst);
	}

	/*
	 * public List<Produto> getPesquisa(Produto prod) { return
	 * cProduto.getPesquisa(prod); }
	 * 
	 * public List<Cidade> getPesquisa(Cidade c) { return
	 * cCidade.getPesquisa(c); }
	 */
	public boolean excluir(Produto p) {
		return cProduto.excluir(p);
	}

	public boolean inserir(Cidade c) {
		return cCidade.inserir(c);
	}

	public boolean alterar(Cidade c) {
		return cCidade.alterar(c);
	}

	public boolean excluir(Cidade c) {
		return cCidade.excluir(c);
	}

	public long getProxId() {
		return cCidade.getProxId();
	}

	public List<String> getEstado() {
		return cCidade.getEstado();
	}

	public List<Cidade> getListaCidadesCod() {
		return cCidade.getListaCod();
	}

	public List<Estoque> getListaEstoque() {
		return cEstoque.getListaEstoque();
	}

	public long proxCodEstoque() {
		return cEstoque.proxCodEstoque();
	}

}
