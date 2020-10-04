import java.sql.*;

public class Conexao {

    private Connection con;
    private final String URL = "jdbc:mysql://localhost:3306/Desafio?useTimezone=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String TPCONEXAO = "com.mysql.cj.jdbc.Driver";

    public Connection abrirConexao() {
        try {
            Class.forName(TPCONEXAO);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }
}