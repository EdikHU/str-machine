package sed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
    static Connection connection;
    static String tableName = "theTable";
    {

    }

	public static void writeCookie(String id,String cookie,String ipAddr,String date) {
		
    	try {
    		
    		if (connection == null){
    	        initConnection();
    		}
    		
            Statement s = connection.createStatement();

//            System.out.println("insert into " + tableName + " values ('" + id + "','" + cookie + "','" + ipAddr + "','" + date + "')");

            s.execute("insert into " + tableName + " values ('" + id + "','" + cookie + "','" + ipAddr + "','" + date + "')");

            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	private static void initConnection() {
		try {
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		    connection = DriverManager.getConnection("jdbc:derby:sampleDB;create=true");
		    try {
		        Statement s = connection.createStatement();
		        s.execute("CREATE TABLE " + tableName
		                + " ( Id varchar(255), Cookie varchar(255),  IpAddr varchar(255), Date varchar(255)  )");
		        s.close();
		    } catch (SQLException e) {
		        System.out.println("exist allready");
		    }

		} catch (InstantiationException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	public static void dbClose() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String showInfo() {
        Statement s;
        StringBuilder sb = new StringBuilder();
		try {
    		if (connection == null){
    	        initConnection();
    		}

			s = connection.createStatement();
			ResultSet rs = s.executeQuery("select * from " + tableName);
	        while (rs.next()) {
	            sb.append ("[" + rs.getString(1) + "][" + rs.getString(2) + "][" + rs.getString(3) + "][" + rs.getString(4) + "]\n");
	        }
	        s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
