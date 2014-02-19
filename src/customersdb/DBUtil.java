package customersdb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

	static Connection connected = null;

	static Connection getConnected(){
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connected = DriverManager.getConnection("jdbc:derby://lockalhost:1527/customers", "sa","");
			return connected;
		} catch (    ClassNotFoundException | SQLException e) {
		}// end try/catch
		return connected;
	}// end getConnected

}// end DBUtil