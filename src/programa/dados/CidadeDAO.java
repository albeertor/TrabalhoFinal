package programa.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Produto;

public class CidadeDAO implements IRepositorioCidade {
	private Connection conexao;
	private long proxId;

	public CidadeDAO() {
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getProxId() {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'cidade'");
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
	
	@Override
	public List<Cidade> getListaCod() {
		List<Cidade> lista = new ArrayList<Cidade>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade ORDER BY cdcidade");
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
			stmt = conexao.prepareStatement("SELECT * FROM cidade GROUP BY sgestado ORDER BY sgestado ASC");
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
	public List<String> getEstado() {
		List<String> lista = new ArrayList<String>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM estado ORDER BY sgestado ASC");
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
			stmt.setString(1, sgEst);
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

	@Override
	public boolean inserir(Cidade c) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("INSERT into cidade(nmCidade, sgESTADO) value (?,?)");

			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getsgEstado());

			stmt.execute();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alterar(Cidade c) {
		if (c != null) {
			try {
				PreparedStatement stmt = conexao
						.prepareStatement("UPDATE cidade SET nmcidade = ?, sgestado = ? WHERE cdcidade=?");

				stmt.setString(1, c.getNome());
				stmt.setString(2, c.getsgEstado());
				stmt.setLong(3, c.getCodCidade());

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
	public boolean excluir(Cidade c) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM cidade WHERE cdcidade=?");
			stmt.setLong(1, c.getCodCidade());

			stmt.execute();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

/*	@Override
	public List<Cidade> getPesquisa(Cidade c) {

		List<Cidade> lista = new ArrayList<Cidade>();

		PreparedStatement stmt;
		if (c != null) {
			if (c.getCodCidade() == 0) {
				if (c.getNome() == null && c.getsgEstado() != null)
					lista = getListaCest(c.getsgEstado());
				else {
					if (c.getsgEstado() == null && c.getNome() != null) {
						try {
							stmt = conexao.prepareStatement(
									"SELECT * FROM cidade WHERE nmcidade like ? ORDER BY nmcidade ASC");
							stmt.setString(1, c.getNome() + "%");
							ResultSet rs = stmt.executeQuery();

							while (rs.next()) {
								Cidade city = new Cidade();
								city.setCodCidade(rs.getLong("cdcidade"));
								city.setNome(rs.getString("nmcidade"));
								city.setSgEstado(rs.getString("sgestado"));

								lista.add(city);
							}

							rs.close();
							stmt.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						if (c.getsgEstado() != null && c.getNome() != null) {
							try {
								stmt = conexao.prepareStatement(
										"SELECT * FROM cidade WHERE nmcidade like ? and sgestado = ? ORDER BY nmcidade ASC");
								stmt.setString(1, c.getNome() + "%");
								stmt.setString(2, c.getsgEstado());
								ResultSet rs = stmt.executeQuery();

								while (rs.next()) {
									Cidade city = new Cidade();
									city.setCodCidade(rs.getLong("cdcidade"));
									city.setNome(rs.getString("nmcidade"));
									city.setSgEstado(rs.getString("sgestado"));

									lista.add(city);
								}

								rs.close();
								stmt.close();

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				if (c.getNome() == null && c.getsgEstado() != null) {
					try {
						stmt = conexao.prepareStatement(
								"SELECT * FROM cidade WHERE sgestado = ? and cdcidade = ? ORDER BY nmcidade ASC");
						stmt.setString(1, c.getsgEstado());
						stmt.setLong(2, c.getCodCidade());
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {
							Cidade city = new Cidade();
							city.setCodCidade(rs.getLong("cdcidade"));
							city.setNome(rs.getString("nmcidade"));
							city.setSgEstado(rs.getString("sgestado"));

							lista.add(city);
						}

						rs.close();
						stmt.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				else {
					if (c.getsgEstado() == null && c.getNome() != null) {
						try {
							stmt = conexao.prepareStatement(
									"SELECT * FROM cidade WHERE nmcidade like ? and cdcidade = ? ORDER BY nmcidade ASC");
							stmt.setString(1, c.getNome() + "%");
							stmt.setLong(2, c.getCodCidade());
							ResultSet rs = stmt.executeQuery();

							while (rs.next()) {
								Cidade city = new Cidade();
								city.setCodCidade(rs.getLong("cdcidade"));
								city.setNome(rs.getString("nmcidade"));
								city.setSgEstado(rs.getString("sgestado"));

								lista.add(city);
							}

							rs.close();
							stmt.close();

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						if (c.getsgEstado() == null && c.getNome() == null) {
							try {
								stmt = conexao.prepareStatement(
										"SELECT * FROM cidade WHERE cdcidade = ? ORDER BY nmcidade ASC");

								stmt.setLong(1, c.getCodCidade());
								ResultSet rs = stmt.executeQuery();

								while (rs.next()) {
									Cidade city = new Cidade();
									city.setCodCidade(rs.getLong("cdcidade"));
									city.setNome(rs.getString("nmcidade"));
									city.setSgEstado(rs.getString("sgestado"));

									lista.add(city);
								}

								rs.close();
								stmt.close();

							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							if (c.getsgEstado() != null && c.getNome() != null) {
								try {
									stmt = conexao.prepareStatement(
											"SELECT * FROM cidade WHERE cdcidade = ? and nmcidade like ? and sgestado = ?  ORDER BY nmcidade ASC");

									stmt.setLong(1, c.getCodCidade());
									stmt.setString(2, c.getNome() + "%");
									stmt.setString(3, c.getsgEstado());
									ResultSet rs = stmt.executeQuery();

									while (rs.next()) {
										Cidade city = new Cidade();
										city.setCodCidade(rs.getLong("cdcidade"));
										city.setNome(rs.getString("nmcidade"));
										city.setSgEstado(rs.getString("sgestado"));

										lista.add(city);
									}

									rs.close();
									stmt.close();

								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		return lista;
	}*/
}
