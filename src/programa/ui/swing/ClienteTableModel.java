package programa.ui.swing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import programa.negocio.entidades.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	private List<Cliente> linhas;
	private String[] colunas = new String[] { "Código", "Nome", "Data de Nascimento","Telefone",
		"RG","CPF","CEP","Endereço","Cidade" };
	
	private static final int MAT = 0;
	private static final int NOME = 1;
	private static final int DATANASC = 2;
	private static final int RG = 4;
	private static final int CPF = 5;
	private static final int CEP = 6;
	private static final int ENDERECO = 7;
	private static final int CIDADE = 8;
	private static final int TELEFONE = 3;

	public ClienteTableModel(List<Cliente> clientes) {
				super();
		
		linhas = new ArrayList<Cliente>(clientes);
	}

	public Cliente getAluno(int indice) {
		return linhas.get(indice);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case MAT:
			return Long.class;
		case NOME:
			return String.class;
		case DATANASC:
			return String.class;
		case RG:
			return String.class;
		case CPF:
			return String.class;
		case CIDADE:
			return String.class;
		case ENDERECO:
			return String.class;
		case CEP:
			return String.class;
		case TELEFONE:
			return String.class;
		default:
			
			throw new IndexOutOfBoundsException(
					"Ultrapassou os limites das colunas");
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = linhas.get(rowIndex);

		switch (columnIndex) {
		case MAT:
			return cliente.getCodCliente();
		case NOME:
			return cliente.getNome();
		case DATANASC:
			return cliente.getDateFormat();
		case RG:
			return cliente.getRg();
		case CPF:
			return cliente.getCpf();
		case CIDADE:
			return cliente.getCidade().getNome();
		case ENDERECO:
			return cliente.getEndereco();
		case CEP:
			return cliente.getCep();
		case TELEFONE:
			return cliente.getTel();
		default:
			
			throw new IndexOutOfBoundsException(
					"Ultrapassou os limites das colunas");
		}
	}

}
