package programa.dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

public class ClienteDAO implements IRepositorioCliente {
	private Connection conexao;
	private long proxId;

	public ClienteDAO() {
		try {
			conexao = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean inserir(Cliente c) {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"INSERT into cliente(nmCliente, dtnasc, cpf, rg, cep, endereco, cdcidade,fone) value (?,?,?,?,?,?,?,?)");

			stmt.setString(1, c.getNome());
			Date d = new Date(c.getDtNasc().getTime());
			stmt.setDate(2, d);
			stmt.setString(3, c.getCpf());
			stmt.setString(4, c.getRg());
			stmt.setString(5, c.getCep());
			stmt.setString(6, c.getEndereco());
			stmt.setLong(7, c.getCidade().getCodCidade());
			stmt.setString(8, c.getTel());

			stmt.execute();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean excluir(Cliente c) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM cliente WHERE cdCliente=?");
			stmt.setLong(1, c.getCodCliente());

			stmt.execute();
			stmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Cidade getCidade(int cdCidade) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cidade WHERE cdcidade=?");
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
	public List<Cliente> getLista() {
		List<Cliente> lista = new ArrayList<Cliente>();

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM cliente");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCodCliente(rs.getLong("cdCliente"));
				c.setNome(rs.getString("nmCliente"));
				c.setDtAniversario(rs.getDate("dtNasc"));
				c.setCpf(rs.getString("cpf"));
				c.setRg(rs.getString("rg"));
				c.setEndereco(rs.getString("endereco"));

				int cdcid = rs.getInt("cdCidade");
				Cidade cid = getCidade(cdcid);

				c.setCidade(cid);
				c.setTel(rs.getString("fone"));
				c.setCep(rs.getString("cep"));
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
	public Cliente buscaCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getProxId() {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'cliente'");
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
	public boolean alterar(Cliente c) {
		if (c != null) {
			try {
				PreparedStatement stmt = conexao.prepareStatement(
						"UPDATE cliente SET nmCliente=?, dtNasc=?, cpf=?, rg=?, cep=?, endereco=?, cdcidade=?, fone=? WHERE cdCliente=?");

				stmt.setString(1, c.getNome());
				Date d = new Date(c.getDtNasc().getTime());
				stmt.setDate(2, d);
				stmt.setString(3, c.getCpf());
				stmt.setString(4, c.getRg());
				stmt.setString(5, c.getCep());
				stmt.setString(6, c.getEndereco());
				stmt.setLong(7, c.getCidade().getCodCidade());
				stmt.setString(8, c.getTel());
				stmt.setLong(9, c.getCodCliente());

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

/*	@Override
	public List<Cliente> getPesquisa(Cliente c) {
		List<Cliente> lista = new ArrayList<Cliente>();

		PreparedStatement stmt;
		if (c.getNome() == null && c.getCpf() == null && c.getCodCliente() == 0 && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cdcidade = ?");

				stmt.setLong(1, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (c.getNome() != null && c.getCpf() != null && c.getCodCliente() != 0 && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement(
						"SELECT * FROM cliente WHERE nmcliente like ? and cpf = ? and cdcliente = ? and cdcidade = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setString(2, c.getCpf());

				stmt.setLong(3, c.getCodCliente());

				stmt.setLong(4, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCpf() == null && c.getCodCliente() != 0 && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cdcliente = ? and cdcidade = ?");

				stmt.setLong(1, c.getCodCliente());
				stmt.setLong(2, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() != null && c.getCpf() != null && c.getCodCliente() != 0 && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement(
						"SELECT * FROM cliente WHERE  nmcliente like ? and cpf = ? and cdcliente = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setString(2, c.getCpf());
				stmt.setLong(3, c.getCodCliente());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCodCliente() == 0 && c.getCpf() != null && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ? and cdcidade = ?");

				stmt.setString(1, c.getCpf());

				stmt.setLong(2, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCodCliente() != 0 && c.getCpf() != null && c.getCidade() != null) {
			try {
				stmt = conexao
						.prepareStatement("SELECT * FROM cliente WHERE cpf = ? and cdcliente = ? and cdcidade = ?");

				stmt.setString(1, c.getCpf());

				stmt.setLong(2, c.getCodCliente());

				stmt.setLong(3, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCpf() == null && c.getCodCliente() != 0 && c.getNome() != null && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement(
						"SELECT * FROM cliente WHERE nmcliente like ? and cdcliente = ? and cdcidade = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setLong(2, c.getCodCliente());

				stmt.setLong(3, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCodCliente() == 0 && c.getNome() != null && c.getCpf() != null && c.getCidade() != null) {
			try {
				stmt = conexao
						.prepareStatement("SELECT * FROM cliente WHERE nmcliente like ? and cpf = ? and cdcidade = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setString(2, c.getCpf());

				stmt.setLong(3, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCpf() == null && c.getCodCliente() == 0 && c.getNome() != null && c.getCidade() != null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE nmcliente like ? and cdcidade = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setLong(2, c.getCidade().getCodCidade());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCpf() == null && c.getCodCliente() != 0 && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cdcliente = ?");

				stmt.setLong(1, c.getCodCliente());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCodCliente() == 0 && c.getCpf() != null && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ?");

				stmt.setString(1, c.getCpf());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getNome() == null && c.getCodCliente() != 0 && c.getCpf() != null && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ? and cdcliente = ?");

				stmt.setString(1, c.getCpf());

				stmt.setLong(2, c.getCodCliente());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCpf() == null && c.getCodCliente() != 0 && c.getNome() != null && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE nmcliente like ? and cdcliente = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setLong(2, c.getCodCliente());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCodCliente() == 0 && c.getNome() != null && c.getCpf() != null && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE nmcliente like ? and cpf = ?");

				stmt.setString(1, c.getNome() + "%");

				stmt.setString(2, c.getCpf());

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (c.getCpf() == null && c.getCodCliente() == 0 && c.getNome() != null && c.getCidade() == null) {
			try {
				stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE nmcliente like ?");

				stmt.setString(1, c.getNome() + "%");

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodCliente(rs.getLong("cdCliente"));
					cliente.setNome(rs.getString("nmCliente"));
					cliente.setDtAniversario(rs.getDate("dtNasc"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setEndereco(rs.getString("endereco"));

					int cdcid = rs.getInt("cdCidade");
					Cidade cid = getCidade(cdcid);

					cliente.setCidade(cid);
					cliente.setTel(rs.getString("fone"));
					cliente.setCep(rs.getString("cep"));
					lista.add(cliente);
				}

				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
*/
}
