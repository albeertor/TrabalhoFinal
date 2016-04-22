package programa.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Cidade;

public class CidadeDAO implements IRepositorioCidade {
	private Connection conexao;

	public CidadeDAO() {
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cidade> getLista() {
		List<Cidade> lista = new ArrayList<Cidade>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade ORDER BY nmcidade ASC");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cidade c = new Cidade();
				c.setCodCidade(rs.getLong("cdcidade"));
				c.setNome(rs.getString("nmcidade"));
				c.setSgEstado(rs.getString("sgestado"));
			
				lista.add(c);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public Cidade getCidade(int cdCidade) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade WHERE cdcidade = ?");
			stmt.setInt(1, cdCidade);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cidade c = new Cidade();
				c.setCodCidade(rs.getLong("cdcidade"));
				c.setNome(rs.getString("nmcidade"));
				c.setSgEstado(rs.getString("sgestado"));	
				
				return c;
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}

	@Override
	public List<String> getListaEstado() {
		List<String> lista = new ArrayList<String>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade GROUP BY sgestado");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(rs.getString("sgestado"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Cidade> getListaCest(String sgEst) {
		List<Cidade> lista = new ArrayList<Cidade>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade WHERE sgestado = ? ORDER BY nmcidade ASC");
			stmt.setString(1	, sgEst);	
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cidade c = new Cidade();
				c.setCodCidade(rs.getLong("cdcidade"));
				c.setNome(rs.getString("nmcidade"));
				c.setSgEstado(rs.getString("sgestado"));
			
				lista.add(c);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
