package studentInterfaceDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;
import org.omg.CORBA.Request;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.StudentDetail;
import connection.ConnectionProvider;
import studentInterfaceDao.Student;

public class StudentImpl2 implements Student{

	
	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	StudentDetail studentDetail;
	List<String> arrayList;
	List<String> arrayList2;
	

	@Override
	public void tableCreate()throws SQLException 
	{
		 studentDetail=new StudentDetail();
		try{
		connection=ConnectionProvider.getConnection();
	@SuppressWarnings("resource")
	Scanner scanner=new Scanner(System.in);
	System.out.println("Please enter table name");
	String tableName=scanner.nextLine();
	String createTable="create table " +tableName+" (id INT not null auto_increment , PRIMARY KEY(id))";
	
	statement=(Statement) connection.createStatement();
	statement.executeUpdate(createTable);
	System.out.println("Please enter column size");
	int columnSize=scanner.nextInt();
	String columnName;
	String sqlQuery;
	arrayList=new ArrayList<>();
	Scanner scan=new Scanner(System.in);
	for(int i=0;i<columnSize;i++)
	{
		System.out.println("Please enter column name"+(i+1));
		columnName=scan.nextLine();
		arrayList.add(columnName);
		studentDetail.setArrayList(arrayList);
		sqlQuery="alter table "+tableName+" add column "+ columnName +"  VARCHAR(50)";
		statement.executeUpdate(sqlQuery);
	}
	/*System.out.println(studentDetail.getArrayList());*/
		
	}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(studentDetail.getArrayList());
	}
	

	@Override
	public void insert() {
		StudentDetail student_detail=new StudentDetail();
		
		
		try 
		{
			connection=ConnectionProvider.getConnection();
			String query="insert into StrudentData (name,id,address,mobile) values(?,?,?,?)";
			preparedStatement=(PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1,student_detail.getName());
			 preparedStatement.setString(2,student_detail.getId());
			 preparedStatement.setString(3,student_detail.getAddress());
			 preparedStatement.setString(4,student_detail.getMobile());
			 preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void retrieveData() {
		// TODO Auto-generated method stub
		
	}

}
