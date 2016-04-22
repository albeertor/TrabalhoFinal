package programa.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import programa.ui.fx.UICliente;
import programa.ui.swing.UICidade;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class JanelaPrincipal {

	private JFrame frame;
	private UICliente uiCliente;
	private JButton btInserir;
	private JButton btAlterar;
	private JButton btExcluir;
	private JButton btListar;
	
	public JanelaPrincipal(UICliente uiCliente, UICidade uiCidade) {
		this.uiCliente = uiCliente;
		initGui();
		frame.setVisible(true);
	}

	private void initGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Janela Principal");

		frame.setSize(400, 273);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JMenuBar barra = new JMenuBar();
		frame.setJMenuBar(barra);

		JMenu menuCliente = new JMenu("Cliente");
		barra.add(menuCliente);

		JMenuItem itemClienteInserir = new JMenuItem("Inserir");
		itemClienteInserir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				uiCliente.inserirCliente();

			}
		});

		menuCliente.add(itemClienteInserir);

		JMenuItem itemAlunoAlterar = new JMenuItem("Alterar");
		itemAlunoAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiCliente.alterarCliente();

			}
		});
		menuCliente.add(itemAlunoAlterar);

		JMenuItem itemAlunoExcluir = new JMenuItem("Excluir");
		itemAlunoExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiCliente.excluirCliente();

			}
		});
		menuCliente.add(itemAlunoExcluir);

		JMenuItem itemAlunoListar = new JMenuItem("Listar");
		itemAlunoListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiCliente.listarClientes();
			}
		});		
		menuCliente.add(itemAlunoListar);
		
		JMenu menuVenda = new JMenu("Venda");
		barra.add(menuVenda);
		
		JMenuItem itemVendaNova = new JMenuItem("Nova");
		
		menuVenda.add(itemVendaNova);
		
		JMenuItem itemVendaPesq = new JMenuItem("Pesquisar");
		
		menuVenda.add(itemVendaPesq);
		
		
//		frame.getContentPane().setLayout(null);
//
//		btInserir = new JButton("Inserir");
//		btInserir.setFont(new Font("Tahoma", Font.PLAIN, 20));
//
//		btInserir.setBounds(54, 67, 139, 57);
//		btInserir.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				uiCliente.inserirCliente();
//
//			}
//		});
//		frame.getContentPane().add(btInserir);
//
//		btListar = new JButton("Listar");
//		btListar.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		btListar.setBounds(198, 67, 139, 57);
//		btListar.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				uiCliente.listarClientes();
//
//			}
//		});
//		frame.getContentPane().add(btListar);
//
//		btAlterar = new JButton("Alterar");
//		btAlterar.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		btAlterar.setBounds(54, 131, 139, 57);
//		btAlterar.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				uiCliente.alterarCliente();
//			}
//		});
//		frame.getContentPane().add(btAlterar);
//
//		btExcluir = new JButton("Excluir");
//		btExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		btExcluir.setBounds(198, 131, 139, 57);
//		btExcluir.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				uiCliente.excluirCliente();
//			}
//		});
//		frame.getContentPane().add(btExcluir);
//
//		JLabel lblControleCliente = new JLabel("Controle Cliente");
//		lblControleCliente.setHorizontalAlignment(SwingConstants.CENTER);
//		lblControleCliente.setFont(new Font("Tahoma", Font.PLAIN, 30));
//		lblControleCliente.setBounds(0, 0, 394, 56);
//		frame.getContentPane().add(lblControleCliente);
	}
}
