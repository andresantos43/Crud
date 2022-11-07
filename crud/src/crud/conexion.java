package crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	Connection cx=null;
	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
		    cx = DriverManager.getConnection("jdbc:sqlite:sistema.db");
		    System.out.println("Conexion exitosa");
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return cx;
	}
	public static void main(String [] args ) {
		conexion cx=new conexion();
		cx.conectar();
		
		}
			
		


}
