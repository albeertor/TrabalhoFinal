package programa.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Produto;

public class ProdutoDAO implements IRepositorioProduto {

	private Connection conexao;
	private long proxId;

	public ProdutoDAO() {
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean inserir(Produto prod) {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"INSERT into produto(cdproduto, nmproduto, descricao, vlunit, quant) value (?,?,?,?,?)");

			stmt.setLong(1, prod.getCod());
			stmt.setString(2, prod.getNome());
			stmt.setString(3, prod.getDesc());
			stmt.setDouble(4, prod.getVlUnit());
			stmt.setInt(5, prod.getQtd());

			stmt.execute();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public long proxCod() {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'produto'");
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

	@Override
	public boolean alterar(Produto prod) {

		if (prod != null) {
			try {
				PreparedStatement stmt = conexao.prepareStatement(
						"UPDATE produto SET nmproduto=?, descricao=?, vlunit=?, quant=? WHERE cdproduto=?");

				stmt.setString(1, prod.getNome());
				stmt.setString(2, prod.getDesc());
				stmt.setDouble(3, prod.getVlUnit());
				stmt.setInt(4, prod.getQtd());
				stmt.setLong(5, prod.getCod());

				stmt.execute();
				stmt.close();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();

				return false;
			}
		}

		return false;
	}

	@Override
	public List<Produto> getLista() {

		List<Produto> lista = new ArrayList<Produto>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM produto");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Produto p = new Produto();
				p.setCod(rs.getInt("cdproduto"));
				p.setNome(rs.getString("nmproduto"));
				p.setDesc(rs.getString("descricao"));
				p.setQtd(rs.getInt("quant"));
				p.setVlUnit(rs.getDouble("vlunit"));

				lista.add(p);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	@Override
	public List<Produto> getPesquisa(Produto prod) {

		List<Produto> lista = new ArrayList<Produto>();

		PreparedStatement stmt;
		if (prod != null) { 
			if (prod.getCod() == 0) {
				try {
					stmt = conexao.prepareStatement("SELECT * FROM produto WHERE nmproduto like ?");
					stmt.setString(1, prod.getNome() + "%");
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {

						Produto p = new Produto();
						p.setCod(rs.getInt("cdproduto"));
						p.setNome(rs.getString("nmproduto"));
						p.setDesc(rs.getString("descricao"));
						p.setQtd(rs.getInt("quant"));
						p.setVlUnit(rs.getDouble("vlunit"));

						lista.add(p);
					}

					rs.close();
					stmt.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				if (prod.getNome() == null) {

					try {
						stmt = conexao.prepareStatement("SELECT * FROM produto WHERE cdproduto = ?");
						stmt.setLong(1, prod.getCod());
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {

							Produto p = new Produto();
							p.setCod(rs.getInt("cdproduto"));
							p.setNome(rs.getString("nmproduto"));
							p.setDesc(rs.getString("descricao"));
							p.setQtd(rs.getInt("quant"));
							p.setVlUnit(rs.getDouble("vlunit"));

							lista.add(p);
						}

						rs.close();
						stmt.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					try {
						stmt = conexao.prepareStatement("SELECT * FROM produto WHERE cdproduto = ?, nmproduto like ?");
						stmt.setLong(1, prod.getCod());
						stmt.setString(2, prod.getNome() + "%");
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {

							Produto p = new Produto();
							p.setCod(rs.getInt("cdproduto"));
							p.setNome(rs.getString("nmproduto"));
							p.setDesc(rs.getString("descricao"));
							p.setQtd(rs.getInt("quant"));
							p.setVlUnit(rs.getDouble("vlunit"));

							lista.add(p);
						}

						rs.close();
						stmt.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return lista;
	}

	@Override
	public boolean excluir(Produto prod) {
		if (prod != null) {
			try {
				PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produto WHERE cdproduto=?");
				stmt.setLong(1, prod.getCod());

				stmt.execute();
				stmt.close();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
