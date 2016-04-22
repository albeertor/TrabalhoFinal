package programa.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import programa.negocio.entidades.Cidade;
import programa.negocio.entidades.Cliente;

public class JanelaClienteInserir implements ActionListener {

	private Cliente cliente;
	private JDialog frame;
	private JPanel panel;
	private JLabel lCod;
	private JLabel lNome;
	private JLabel lData;
	private JLabel lRG;
	private JLabel lCPF;
	private JTextField fCod;
	private JTextField fNome;
	private JFormattedTextField fData;
	private JTextField fRG;
	private JFormattedTextField fCPF;

	private JPanel panelBotoes;
	private JTextField fTelefone;
	private JLabel lCep;
	private JTextField fCep;
	private JLabel lEndereo;
	private JTextField fEndereco;
	private JLabel lCidade;
	private JComboBox<String> cidade;
	private JLabel lSiglaDoEstado;
	private JTextField sgestado;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JLabel lTelefone;

	private List<Cidade> lista = new ArrayList<Cidade>();
	
	public void initGUI(long id) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}

		frame = new JDialog();
		frame.setTitle("Cadastro de Cliente");

		frame.setModal(true);
		frame.setSize(285, 500);

		frame.setResizable(false);

		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Texto
		lCod = new JLabel("Código do Cliente:");
		lCod.setBounds(10, 11, 119, 25);
		panel.add(lCod);

		lNome = new JLabel("Nome:");
		lNome.setBounds(10, 66, 100, 25);
		panel.add(lNome);

		lData = new JLabel("Data de Nascimento:");
		lData.setBounds(10, 129, 100, 25);
		panel.add(lData);

		lRG = new JLabel("RG:");
		lRG.setBounds(150, 190, 100, 25);
		panel.add(lRG);

		lCPF = new JLabel("CPF:");
		lCPF.setBounds(10, 190, 100, 25);
		panel.add(lCPF);

		lTelefone = new JLabel("Telefone:");
		lTelefone.setBounds(150, 134, 65, 14);
		panel.add(lTelefone);

		lCep = new JLabel("CEP:");
		lCep.setBounds(10, 257, 46, 14);
		panel.add(lCep);

		lEndereo = new JLabel("Endereço:");
		lEndereo.setBounds(10, 314, 65, 14);
		panel.add(lEndereo);

		lCidade = new JLabel("Cidade");
		lCidade.setBounds(10, 375, 46, 14);
		panel.add(lCidade);

		lSiglaDoEstado = new JLabel("Sigla do Estado:");
		lSiglaDoEstado.setBounds(150, 375, 100, 14);
		panel.add(lSiglaDoEstado);

		// Campo Texto
		fCod = new JTextField();
		fCod.setText(String.valueOf(id));

		fCod.setEditable(false);
		fCod.setBounds(10, 37, 119, 25);
		panel.add(fCod);

		fNome = new JTextField();
		fNome.setBounds(10, 93, 257, 25);
		panel.add(fNome);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		fData = new JFormattedTextField(mascara);
		fData.setBounds(10, 154, 119, 25);
		panel.add(fData);

		MaskFormatter mascaraRg = null;
		try {
			mascaraRg = new MaskFormatter("**********");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		fRG = new JFormattedTextField(mascaraRg);
		fRG.setBounds(150, 216, 117, 25);
		panel.add(fRG);

		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		fCPF = new JFormattedTextField(mascaraCpf);
		fCPF.setBounds(10, 216, 119, 25);
		panel.add(fCPF);
		
		
		
		MaskFormatter mascaraTel = null;
		try {
			mascaraTel = new MaskFormatter("(##) ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		fTelefone = new JFormattedTextField(mascaraTel);
		fTelefone.setBounds(150, 154, 119, 25);
		panel.add(fTelefone);

		MaskFormatter mascaraCep = null;
		try {
			mascaraCep = new MaskFormatter("#####-###");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		fCep = new JFormattedTextField(mascaraCep);
		fCep.setBounds(10, 278, 119, 25);
		panel.add(fCep);
			
		fEndereco = new JTextField();
		fEndereco.setColumns(11);
		fEndereco.setBounds(10, 339, 257, 25);
		panel.add(fEndereco);

		cidade = new JComboBox<String>();
		cidade.setBounds(10, 394, 119, 25);
		panel.add(cidade);

		cidade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sgEstado = null;
				String nmCidade = (String) cidade.getSelectedItem();

				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).getNome() == nmCidade)
						sgEstado = lista.get(i).getsgEstado();
				}

				sgestado.setText(sgEstado);
			}
		});

		sgestado = new JTextField();
		sgestado.setBounds(150, 394, 119, 25);
		panel.add(sgestado);

		sgestado.setEditable(false);

		panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		frame.getContentPane().add(panelBotoes, BorderLayout.SOUTH);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(410, 574, 119, 23);
		btnSalvar.addActionListener(this);
		panelBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(550, 574, 119, 23);
		btnCancelar.addActionListener(this);
		panelBotoes.add(btnCancelar);

	}

//	public JanelaClienteInserir(long id, List<Cidade> cidades) {
//		lista = cidades;
//
//		initGUI(id);
//
//		String[] nmcidades = new String[cidades.size()];
//		for (int i = 0; i < cidades.size(); i++) {
//			nmcidades[i] = cidades.get(i).getNome();
//			cidade.addItem(nmcidades[i]);
//
//		}
//
//		String sgEstado = null;
//		String nmCidade = (String) cidade.getSelectedItem();
//
//		for (int i = 0; i < lista.size(); i++) {
//			if (lista.get(i).getNome() == nmCidade)
//				sgEstado = lista.get(i).getsgEstado();
//		}
//		sgestado.setText(sgEstado);
//
//		frame.setVisible(true);
//	}
	
	public JanelaClienteInserir(Cliente c, List<Cidade> cidades) {

		lista = cidades;

		initGUI(c.getCodCliente());
		fNome.setText(c.getNome());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(c.getDtNasc());
		fData.setText(data);
		fRG.setText(c.getRg());
		fCPF.setText(c.getCpf());
		fCep.setText(c.getCep());
		fEndereco.setText(c.getEndereco());
		fTelefone.setText(c.getTel());
		String[] nmcidades = new String[cidades.size()];
		for (int i = 0; i < cidades.size(); i++) {
			nmcidades[i] = cidades.get(i).getNome();
			cidade.addItem(nmcidades[i]);
		}

		cidade.setSelectedItem(c.getCidade().getNome());
		sgestado.setText(c.getCidade().getsgEstado());

		frame.setVisible(true);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == btnSalvar) {
			boolean validacao = true;

			if (fNome.getText().equals("")) {
				fNome.setBackground(Color.PINK);
				validacao = false;
			} else
				fNome.setBackground(Color.WHITE);

			if (fData.getText().equals("  /  /    ")) {
				fData.setBackground(Color.PINK);
				validacao = false;
			} else
				fData.setBackground(Color.WHITE);

			if (fRG.getText().equals("")) {
				fRG.setBackground(Color.PINK);
				validacao = false;
			} else
				fRG.setBackground(Color.WHITE);

			if (!validarCPF.validarCpf(fCPF.getText().replaceAll("\\D",""))) {
				fCPF.setBackground(Color.PINK);
				validacao = false;
				JOptionPane.showMessageDialog(frame, "CPF inválido", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else
				fCPF.setBackground(Color.WHITE);

			if (fCep.getText().equals("     -   ")) {
				fCep.setBackground(Color.PINK);
				validacao = false;
			} else
				fCep.setBackground(Color.WHITE);

			if (fEndereco.getText().equals("")) {
				fEndereco.setBackground(Color.PINK);
				validacao = false;
			} else
				fEndereco.setBackground(Color.WHITE);

			if (fTelefone.getText().equals("(  )     -    ")) {
				fTelefone.setBackground(Color.PINK);
				validacao = false;
			} else
				fTelefone.setBackground(Color.WHITE);
			
			Date dataNasc = null;
			
			if(!fData.getText().equals("  /  /    ")){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String ds = fData.getText();
			try {
				dataNasc = sdf.parse(ds);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}			
			Date dtAtual = Calendar.getInstance().getTime();
				if(dataNasc.after(dtAtual)){
					fData.setBackground(Color.pink);
					JOptionPane.showMessageDialog(frame, "Data inválida", "Erro",
							JOptionPane.ERROR_MESSAGE);
					validacao = false;
					
				}else
					fData.setBackground(Color.WHITE);
			}
			
			
			
			if (validacao == true) {
				Long cod = Long.parseLong(fCod.getText());
				String nome = fNome.getText();
								
				
				String cpf = fCPF.getText();
				String rg = fRG.getText();
				String tel = fTelefone.getText();
				String cep = fCep.getText();
				String endereco = fEndereco.getText();

				Cidade c = null;
				String nmCidade = (String) cidade.getSelectedItem();
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).getNome() == nmCidade)
						c = lista.get(i);
				}

				cliente = Cliente.newInstance(nome, dataNasc, tel, cpf, rg, cep, endereco, c);
				cliente.setCodCliente(cod);
				frame.dispose();
			} else{
				JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos corretamentes", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (o == btnCancelar) {
			frame.dispose();
		}
	}
	
}
