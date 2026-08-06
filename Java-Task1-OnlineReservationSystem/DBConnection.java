package reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
	private static String url="jdbc:mysql://localhost:3306/reservationdb";
	private static 	String username="root";
	private static String password="Sangola";

	public static Connection getConnection()throws Exception {
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");  // JDBC driver load karo
		        } catch (ClassNotFoundException e) {
		            System.out.println("MySQL JDBC Driver not found!");
		            e.printStackTrace();
		        }
		        return DriverManager.getConnection(url,username,password);
			}
		


	}

	
	


