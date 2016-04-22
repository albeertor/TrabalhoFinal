package programa.dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
