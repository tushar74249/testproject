
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class passwordconnect {
	public static Connection getConnection() {
			
		Connection con=null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/password manager","root","");
			System.out.println("connected");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[]) {
		Connection conref=getConnection();
	}
}