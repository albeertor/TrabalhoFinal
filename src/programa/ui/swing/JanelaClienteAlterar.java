package programa.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.UICliente;

public class JanelaClienteAlterar extends JanelaClienteLista {

	private JButton btAlterar;
	protected Cliente selectedCliente;

	public JanelaClienteAlterar(List<Cliente> clientes, List<Cidade> cidades,
			UICliente uiCliente) {
		super(clientes, cidades, uiCliente);
	}

	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	@Override
	protected void initGUI(List<Cliente> clientes,List<Cidade> cidade) {
		super.initGUI(clientes,cidade);

		frame.setTitle("Selecione um cliente para alterar");

		btAlterar = new JButton("Alterar");

		btAlterar.setEnabled(false);
		btAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedLine = tbClientes.getSelectedRow();
				selectedCliente = tmCliente.getAluno(selectedLine);
				frame.dispose();
			}
		});

		panelB.add(btAlterar);

		tbClientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						btAlterar.setEnabled(true);
					}
				});
	}

}
