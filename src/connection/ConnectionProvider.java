package connection;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConnectionProvider 
{
	static java.sql.Connection conn=null;
	private ConnectionProvider()
	{}

	public static synchronized Connection getConnection()
	{
		if(conn==null)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dhananjay","root","root");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return (Connection) conn;
	}
}
