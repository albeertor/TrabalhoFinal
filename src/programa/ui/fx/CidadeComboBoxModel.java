package programa.ui.fx;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import programa.negocio.entidades.Cidade;

public class CidadeComboBoxModel extends AbstractListModel<Object> implements
		ComboBoxModel<Object> {

	private List<Cidade> lista;
	private Cidade selectedCidade;

	public CidadeComboBoxModel() {
		lista = new ArrayList<Cidade>();
	}

	public CidadeComboBoxModel(List<Cidade> cidades) {
		this();
		this.lista.addAll(cidades);
	}

	@Override
	public Object getElementAt(int index) {
		return lista.get(index);
	}

	@Override
	public int getSize() {
		return lista.size();
	}

	@Override
	public Object getSelectedItem() {
		return selectedCidade;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedCidade = (Cidade) anItem;
	}

}
