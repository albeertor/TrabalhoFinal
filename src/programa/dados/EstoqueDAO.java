package programa.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Estoque;
import programa.negocio.entidades.Produto;

public class EstoqueDAO implements IRepositorioEstoque{

	private Connection conexao;
	private long proxId;

	public EstoqueDAO() {
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Produto getProduto(long cdProduto) {

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM produto WHERE cdproduto = ?");
			stmt.setLong(1, cdProduto);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto p = new Produto();
				p.setCod(rs.getInt("cdproduto"));
				p.setNome(rs.getString("nmproduto"));
				p.setDesc(rs.getString("descricao"));
				p.setVlUnit(rs.getDouble("vlunit"));
				
				return p;
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
		
	@Override
	public List<Estoque> getLista() {
		List<Estoque> lista = new ArrayList<Estoque>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM estoque");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Estoque e = new Estoque();
				e.setCod(rs.getInt("cdestoque"));				
				Produto prod = getProduto(rs.getInt("cdproduto"));				
				e.setP(prod);			
				e.setEstoque(rs.getInt("estoque"));
				e.setVlVenda(rs.getDouble("vlvenda"));
				e.setDtEntrada(rs.getDate("dtentrada"));

				lista.add(e);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public long proxCodEstoque() {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'estoque'");
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				proxId = rs.getLong("AUTO_INCREMENT");
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return proxId;
	}

}
