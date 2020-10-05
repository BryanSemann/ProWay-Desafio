import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Crud {
	Conexao con = new Conexao();

	public void create(Pontos p) {

		Connection connection;
		Conexao con = new Conexao();
		connection = con.abrirConexao();

		PreparedStatement pstmt;
		String sql = "INSERT INTO Basquete (placar, pts_max, pts_min, pts_recMax, pts_recMin) VALUES (?, ?, ?, ?, ?)";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, p.getPlacar());
			pstmt.setInt(2, p.getPts_max());
			pstmt.setInt(3, p.getPts_min());
			pstmt.setInt(4, p.getPts_recMax());
			pstmt.setInt(5, p.getPts_recMin());

			pstmt.execute();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro!!!");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Pontos> read(String sql) {

		// Variaveis para Conexao
		Connection connection;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pontos> pontos = new ArrayList<>();

		connection = con.abrirConexao();

		try {

			// PreparedStatement para executar SQL no BD
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// Enquanto existir próximo resultado da pesquisa no banco
			while (rs.next()) {

				Pontos p = new Pontos();

				p.setJogo(rs.getInt("jogo"));
				p.setPlacar(rs.getInt("placar"));
				p.setPts_max(rs.getInt("pts_max"));
				p.setPts_min(rs.getInt("pts_min"));
				p.setPts_recMax(rs.getInt("pts_recMax"));
				p.setPts_recMin(rs.getInt("pts_recMin"));

				// Adiciona o objeto Pontos na lista pontos
				pontos.add(p);
			}

		} catch (SQLException e) {

			// Em caso de falha na conexão envia uma aviso
			e.printStackTrace();
		} finally {

			// Tenta finalizar a conexao
			try {

				// Fechando conexão
				rs.close();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {

				// Em caso de falha na conexão envia uma aviso
				e.printStackTrace();
			}

		}

		// Retorna lista criada do tipo Pontos
		return pontos;
	}

	public void Drop() {

		Connection connection;
		Conexao con = new Conexao();
		connection = con.abrirConexao();

		PreparedStatement pstmt;
		String sqldrop = "Drop table Basquete";
		String sqlcreate = "Create table Basquete (jogo int(4) NOT NULL primary key auto_increment,placar int(4) NOT NULL,pts_max int(4) NOT NULL,pts_min int(4) NOT NULL,pts_recMax int(4) NOT NULL,pts_recMin int(4) NOT NULL);";

		try {
			pstmt = connection.prepareStatement(sqldrop);
			pstmt.execute();
			pstmt = connection.prepareStatement(sqlcreate);
			pstmt.execute();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro!!!");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}