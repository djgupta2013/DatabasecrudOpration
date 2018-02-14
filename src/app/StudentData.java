package app;

import java.sql.SQLException;
import java.util.Scanner;

import connection.ConnectionProvider;
import studentInterfaceDaoImpl.StudentImpl;
import studentInterfaceDaoImpl.StudentImpl2;

public class StudentData 
{

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		ConnectionProvider.getConnection();
		System.out.println(ConnectionProvider.getConnection());
		System.out.println("Please enter your choise");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		System.out.println("Press 1 to create table..");
		System.out.println("Press 2 to Insert data..");
		System.out.println("Press 3 to delete data..");
		System.out.println("Press 4 to update data..");
		System.out.println("Press 5 to retrieve  data..");
		int n=scanner.nextInt();
		StudentImpl studentImpl=new StudentImpl();
		StudentImpl2 studentImpl2=new StudentImpl2();
		switch (n) {
		case 1:
		{
			studentImpl2.tableCreate();
			//studentImpl.tableCreate();
		}
		/*System.out.println("please enter your choice");
		n=scanner.nextInt();*/
		break;
		case 2:
		{	
			studentImpl2.insert();
			
		}
		break;
		case 3:
		{
			studentImpl.delete();
		}
		break;
		case 4:
		{
			studentImpl.update();
		}
		break;
		case 5:
		{
			studentImpl.retrieveData();
		}

		default:
			break;
		}

	}

	

}
