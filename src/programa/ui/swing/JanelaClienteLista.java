package programa.ui.swing;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;
import programa.ui.fx.UICliente;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class JanelaClienteLista {

	private UICliente uiCliente;
	private Cliente clientePesq;
	protected JDialog frame;
	protected JTable tbClientes;
	protected JScrollPane scroll;
	protected ClienteTableModel tmCliente;
	protected JPanel panelB;
	protected JButton btFechar;
	protected JButton btReset;
	private JLabel lPesquisar;
	private JTextField fNome;
	private JTextField fDtAniv;
	private JTextField fCodigo;
	private JComboBox<String> cbCidade;
	private List<Cidade> lista = new ArrayList<Cidade>();
	private JCheckBox chCidade;
	private JCheckBox chCodigo;
	private JCheckBox chDtAniv;
	private JCheckBox chNome;
	private JButton btnLimpar;
	private JButton btnPesquisar;

	public JanelaClienteLista(List<Cliente> clientes, List<Cidade> cidades, UICliente uiCliente) {
		this.uiCliente = uiCliente;

		initGUI(clientes, cidades);

		frame.setVisible(true);
	}

	protected void initGUI(List<Cliente> clientes, List<Cidade> cidades) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		frame = new JDialog();
		frame.setTitle("Listagem de Clientes");

		frame.setModal(true);
		frame.setSize(1120, 500);

		frame.setResizable(false);

		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		tmCliente = new ClienteTableModel(clientes);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().setLayout(null);

		tbClientes = new JTable();
		tbClientes.setModel(tmCliente);

		tbClientes.getColumnModel().getColumn(0).setPreferredWidth(75);
		tbClientes.getColumnModel().getColumn(0).setResizable(false);

		tbClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
		tbClientes.getColumnModel().getColumn(1).setResizable(false);

		tbClientes.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbClientes.getColumnModel().getColumn(2).setResizable(false);

		tbClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbClientes.getColumnModel().getColumn(3).setResizable(false);

		tbClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbClientes.getColumnModel().getColumn(4).setResizable(false);

		tbClientes.getColumnModel().getColumn(5).setPreferredWidth(100);
		tbClientes.getColumnModel().getColumn(5).setResizable(false);

		tbClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
		tbClientes.getColumnModel().getColumn(6).setResizable(false);

		tbClientes.getColumnModel().getColumn(7).setPreferredWidth(200);
		tbClientes.getColumnModel().getColumn(7).setResizable(false);

		tbClientes.getColumnModel().getColumn(8).setPreferredWidth(125);
		tbClientes.getColumnModel().getColumn(8).setResizable(false);

		tbClientes.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		tbClientes.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

		tbClientes.getTableHeader().setReorderingAllowed(false);

		scroll = new JScrollPane(tbClientes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(0, 89, 1094, 338);

		frame.getContentPane().add(scroll);

		panelB = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		panelB.setBounds(0, 428, 1094, 43);
		panelB.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frame.getContentPane().add(panelB);

		btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panelB.add(btFechar);

		btReset = new JButton("Resetar");
		btReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				initGUI(uiCliente.getListaCliente(), lista);
				frame.setVisible(true);

			}
		});
		panelB.add(btReset);

		// PESQUISA
		lPesquisar = new JLabel("Pesquisar por:");
		lPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lPesquisar.setBounds(10, 11, 95, 25);
		frame.getContentPane().add(lPesquisar);

		// MaskFormatter mscCod = null;
		// try {
		// mscCod = new MaskFormatter("##");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		fCodigo = new JFormattedTextField(NumberFormat.getIntegerInstance());
		fCodigo.setBounds(22, 55, 106, 20);
		frame.getContentPane().add(fCodigo);

		MaskFormatter mscDtNasc = null;
		try {
			mscDtNasc = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		fDtAniv = new JFormattedTextField(mscDtNasc);
		fDtAniv.setBounds(437, 55, 131, 20);
		frame.getContentPane().add(fDtAniv);
				
		fNome = new JTextField();
		fNome.setBounds(182, 55, 199, 20);
		frame.getContentPane().add(fNome);
		
		cbCidade = new JComboBox<String>();
		cbCidade.setBounds(624, 55, 153, 20);
		frame.getContentPane().add(cbCidade);

		lista = cidades;
		String[] nmcidades = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			nmcidades[i] = lista.get(i).getNome();
			cbCidade.addItem(nmcidades[i]);
		}

		cbCidade.addItem("QUALQUER CIDADE");

		chCidade = new JCheckBox("Cidade:");
		chCidade.setSelected(true);
		chCidade.setBounds(624, 32, 97, 23);
		frame.getContentPane().add(chCidade);

		chCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbCidade.isEnabled())
					cbCidade.setEnabled(false);
				else
					cbCidade.setEnabled(true);
			}
		});

		chCodigo = new JCheckBox("Código:");
		chCodigo.setBounds(20, 32, 97, 23);
		chCodigo.setSelected(true);
		frame.getContentPane().add(chCodigo);
		chCodigo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fCodigo.isEnabled())
					fCodigo.setEnabled(false);
				else
					fCodigo.setEnabled(true);

			}
		});

		chNome = new JCheckBox("Nome:");
		chNome.setBounds(182, 32, 97, 23);
		chNome.setSelected(true);
		frame.getContentPane().add(chNome);
		chNome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fNome.isEnabled())
					fNome.setEnabled(false);
				else
					fNome.setEnabled(true);

			}
		});

		chDtAniv = new JCheckBox("Data de Aniversário:");
		chDtAniv.setBounds(435, 32, 133, 23);
		chDtAniv.setSelected(true);
		frame.getContentPane().add(chDtAniv);
		chDtAniv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fDtAniv.isEnabled())
					fDtAniv.setEnabled(false);
				else
					fDtAniv.setEnabled(true);

			}
		});

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean validacao = true;
				String dataMasc ="  /  /    ";
				Date dataNasc = null;
				
				if(chCodigo.isSelected() && fCodigo.getText().isEmpty()){
					fCodigo.setBackground(Color.pink);
					validacao = false;
				}else
					fCodigo.setBackground(Color.WHITE);
				
				if(chNome.isSelected() && fNome.getText().isEmpty()){
					fNome.setBackground(Color.pink);
					validacao = false;
				}else
					fNome.setBackground(Color.WHITE);
				
				if(chDtAniv.isSelected() && fDtAniv.getText().equals(dataMasc)){
					fDtAniv.setBackground(Color.pink);
					validacao = false;
				}else
					fDtAniv.setBackground(Color.WHITE);					
				
				if(!fDtAniv.getText().equals("  /  /    ")){
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String ds = fDtAniv.getText();
					try {
						dataNasc = sdf.parse(ds);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}			
					Date dtAtual = Calendar.getInstance().getTime();
						if(dataNasc.after(dtAtual)){
							fDtAniv.setBackground(Color.pink);
							JOptionPane.showMessageDialog(frame, "Data inválida", "Erro",
									JOptionPane.ERROR_MESSAGE);
							validacao = false;
							
						}else
							fDtAniv.setBackground(Color.WHITE);
					}
				
				if (validacao == true) {
					long cod = 0;
					if (chCodigo.isSelected()) {
						if (fCodigo.getText().trim().length() > 0) {
							try {
								cod = NumberFormat.getIntegerInstance().parse(fCodigo.getText()).longValue();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					String nome = null;
					if (chNome.isSelected()) {
						if (fNome.getText() != null) {
							nome = fNome.getText();
						}
					}
					
					if (chDtAniv.isSelected()) {						
						if (!fDtAniv.getText().equals(dataMasc)) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String ds = fDtAniv.getText();
							dataNasc = null;
							try {
								dataNasc = sdf.parse(ds);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						} else
							dataNasc = null;
					}

					Cidade c = null;
					if (chCidade.isSelected()) {
						String nmCidade = (String) cbCidade.getSelectedItem();
						if (!cbCidade.getSelectedItem().equals("QUALQUER UMA")) {
							for (int i = 0; i < lista.size(); i++) {
								if (lista.get(i).getNome() == nmCidade)
									c = lista.get(i);
							}
						}
					}

					clientePesq = Cliente.newInstance(nome, dataNasc, c);

					clientePesq.setCodCliente(cod);
					List<Cliente> cPesq = uiCliente.listarPesquisa(clientePesq);

					frame.dispose();
					initGUI(cPesq, lista);
					frame.setVisible(true);
				}else
					JOptionPane.showMessageDialog(frame, "Preencha corretamente os campos selecionados", "Erro",
							JOptionPane.ERROR_MESSAGE);
				
			}
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fCodigo.setText(null);
				fNome.setText(null);
				fDtAniv.setText(null);
				cbCidade.setSelectedIndex(0);
			}
		});
		btnLimpar.setBounds(916, 54, 79, 23);
		frame.getContentPane().add(btnLimpar);
		btnPesquisar.setBounds(1005, 54, 79, 23);
		frame.getContentPane().add(btnPesquisar);
	}

	public Cliente getCliente() {
		return clientePesq;
	}
}