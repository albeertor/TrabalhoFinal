package programa.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.UICliente;

public class JanelaClienteExcluir extends JanelaClienteLista {

	private JButton btExcluir;
	private Cliente selectedCliente;
	
	public JanelaClienteExcluir(List<Cliente> clientes, List<Cidade> cidades, UICliente uiCliente) {
		super(clientes, cidades,uiCliente);
	}

	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	@Override
	protected void initGUI(List<Cliente> clientes,List<Cidade> cidade) {
		super.initGUI(clientes,cidade);

		frame.setTitle("Selecione um cliente para excluir");

		btExcluir = new JButton("Excluir");
		btExcluir.setEnabled(false);
		btExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Deseja excluir o cliente?", "Excluir Cliente", JOptionPane.YES_NO_OPTION);
				  if (reply == JOptionPane.YES_OPTION)
				  {
						int selectedLine = tbClientes.getSelectedRow();
						selectedCliente = tmCliente.getAluno(selectedLine);
						frame.dispose();
				  }
				
			
			}
		});

		panelB.add(btExcluir);

		tbClientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						btExcluir.setEnabled(true);
					}
				});
	}
}
