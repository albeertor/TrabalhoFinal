package programa.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conexao;

	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		if (conexao == null){
			String database = "baluarte";
			String url = "jdbc:mysql://localhost/"+database;
			String user = "root";
			String password = "";
			conexao = DriverManager.getConnection(url, user, password);			
		}
		return conexao;
	}
	
}
