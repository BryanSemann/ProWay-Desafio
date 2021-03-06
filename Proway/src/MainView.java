import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	private JFrame frmDesafio;
	private JTable table;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmDesafio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainView() {
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		
		frmDesafio = new JFrame();	// Criando a Janela
		frmDesafio.setTitle("Desafio");
		frmDesafio.setBounds(100, 100, 450, 300);
		frmDesafio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDesafio.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Novo Jogo");	// Criando o botao
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calc calc = new Calc();
				
				calc.verifica(Integer.parseInt((textField_1.getText())));	//	Verificando o textfield e inserindo os dados na tabela
				readJTable();	//	Atualizando a tabela
			}
		});
		btnNewButton_1.setBounds(317, 227, 107, 23);
		frmDesafio.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();	//	Criando um painel
		scrollPane.setBounds(10, 11, 414, 200);
		frmDesafio.getContentPane().add(scrollPane);
		
		table = new JTable();	//	Criando a tabela
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Jogo", "Placar", "Max. Pts.", "Min. Pts.", "Max. Recorde", "Min. Recorde" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(49);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(58);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(78);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(83);
		scrollPane.setViewportView(table);

		textField_1 = new JTextField();
		textField_1.setBounds(66, 228, 86, 20);
		frmDesafio.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblPlacar = new JLabel("Placar :");
		lblPlacar.setBounds(10, 231, 46, 14);
		frmDesafio.getContentPane().add(lblPlacar);
		
		JButton btnResetTabela = new JButton("Reset Tabela");	// ============= Criando o botao para reinisiar a tabela =============
		btnResetTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Crud crud = new Crud();
				crud.Drop();	// ============= Removendo elementos da tabela =============
				readJTable();
			}
		});
		btnResetTabela.setBounds(188, 227, 119, 23);
		frmDesafio.getContentPane().add(btnResetTabela);
		readJTable();
	}

	public void readJTable() {

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		Crud crud = new Crud();

		modelo.setNumRows(0);

		// Percorre uma lista de Pontos no banco dedados
		for (Pontos p : crud.read("SELECT * FROM Basquete;")) {

			// popula a tabela com os dados do banco de dados
			modelo.addRow(new Object[] { p.getJogo(), p.getPlacar(), p.getPts_max(), p.getPts_min(), p.getPts_recMax(),
					p.getPts_recMin() });
		}

	}
}
