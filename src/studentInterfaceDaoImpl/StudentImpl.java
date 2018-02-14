package studentInterfaceDaoImpl;

import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.StudentDetail;
import connection.ConnectionProvider;
import exception.MyException;
import studentInterfaceDao.Student;

public class StudentImpl implements Student
{
	StudentDetail student_detail;
	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	
	@Override
	public void tableCreate() {
		
		 try {
	           connection=ConnectionProvider.getConnection();
	           statement=(Statement) connection.createStatement();
	           String ss="CREATE TABLE  StudentData (name varchar(20), id varchar(20),address varchar(100),mobile varchar(10))";
	           statement.execute(ss);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 	
	}
	static boolean validateName(String name)
	{
		if(!name.matches("[a-zA-Z]"))
		{
			return true;
		}
		else
		{
			return false;
			
		}
	
		
		
	}
	static boolean validateId(String id)
	{
		try 
		{
		Integer.parseInt(id);	
		}
		catch (Exception e) {
			return false;
		}
		return true;
		
	}
	static boolean validateMobile(String mobile)
	{
		try 
		{
		Double.valueOf(mobile);	
		}
		catch (Exception e) {
			return false;
		}
		return true;
		
	}
	@Override
	public void insert() 
	{
		boolean flag=true;
		 
		String name,address,id,mobile;
		Scanner scan=new Scanner(System.in);
		StudentDetail student_detail=new StudentDetail();
		System.out.println("Please enter  student name");
	    name=scan.nextLine();
	    if(validateName(name))
	    {
	    	student_detail.setName(name);
	    }
	    else
	    {
	    	
	    	try {
				throw new MyException("please enter valid name ");
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	flag=false;
	    }
		
		System.out.println("Please enter  student id");
		
		id=scan.nextLine();
			if(validateId(id)&&(id.length()<=5))
			{
				student_detail.setId(id);
			}
			else
			{
				try {
					throw new MyException("please enter valid id ");
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag=false;
			}
			
		System.out.println("Please enter  student address");
		address=scan.nextLine();
		student_detail.setAddress(address);
		System.out.println("Please enter  student mobile number");
		mobile=scan.nextLine();
		if(validateMobile(mobile)&&(mobile.length()==10))
		{
			student_detail.setMobile(mobile);
		}
		else
		{
			try {
				throw new MyException("please enter valid number ");
			} catch (MyException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			flag=false;
		}
		//student_detail.setMobile(mobile);
		//System.out.println(student_detail.getName()+" "+student_detail.getId()+" "+student_detail.getAddress()+" "+student_detail.getMobile());
		 
		
		 try {
		   connection=ConnectionProvider.getConnection();
		   if(flag){
			 String ss="insert into StudentData (name,id,address,mobile) values(?,?,?,?)";
			  preparedStatement=(PreparedStatement) connection.prepareStatement(ss);
			 preparedStatement.setString(1,student_detail.getName());
			 preparedStatement.setString(2,student_detail.getId());
			 preparedStatement.setString(3,student_detail.getAddress());
			 preparedStatement.setString(4,student_detail.getMobile());
			 preparedStatement.executeUpdate();
		   }
		   }
		   
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 System.out.println(e);
		 }
		 	
		
		// TODO Auto-generated method stub
		
	}


	

	
	@Override
	public void delete() {
	
		student_detail=new StudentDetail();
		Scanner scan=new Scanner(System.in);
		System.out.println("Please enter id to delete");
		String id=scan.nextLine();
		student_detail.setId(id);
		
		try{
			connection=ConnectionProvider.getConnection();
			String ss="delete from StudentData where id=?";
			preparedStatement=(PreparedStatement) connection.clientPrepareStatement(ss);
			preparedStatement.setString(1,student_detail.getId());
			preparedStatement.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update() 
	{
		StudentDetail studentDetail=new StudentDetail();
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		System.out.println("Press 1 to Update name");
		System.out.println("Press 2 to Update addresse");
		System.out.println("Press 3 to Update mobile number");
		System.out.println("Press 4 to Update id");
		int i=scan.nextInt();
		switch(i)
		{
		case 1 :
		{
			String id,name;
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			//System.out.println("Please enter Student id and name to change student name");
			System.out.println("Please enter student id ");
			id=scanner.nextLine();
			studentDetail.setId(id);
			System.out.println("Please enter student name ");
			name=scanner.nextLine();
			studentDetail.setName(name);
			try{
			connection=ConnectionProvider.getConnection();
			String passData="update StudentData set name=? where id=?";
			preparedStatement=(PreparedStatement) connection.prepareStatement(passData);
			preparedStatement.setString(1, studentDetail.getName());
			preparedStatement.setString(2, studentDetail.getId());
			
			preparedStatement.executeUpdate();
			//connection.close();
			
			}				
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		break;
		case 2 :
		{
			String id,address;
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			//System.out.println("Please enter Student id and address to change student name");
			System.out.println("Please enter student id ");
			id=scanner.nextLine();
			studentDetail.setId(id);
			System.out.println("Please enter student address ");
			address=scanner.nextLine();
			studentDetail.setAddress(address);
			try{
			connection=ConnectionProvider.getConnection();
			String passData="update StudentData set address=? where id=?";
			preparedStatement=(PreparedStatement) connection.prepareStatement(passData);
			preparedStatement.setString(1, studentDetail.getAddress());
			preparedStatement.setString(2, studentDetail.getId());
			
			preparedStatement.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		break;
		case 3 :
		{
			String id,mobile;
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			//System.out.println("Please enter Student id and mobile to change student name");
			System.out.println("Please enter student id ");
			id=scanner.nextLine();
			studentDetail.setId(id);
			System.out.println("Please enter student mobile ");
			mobile=scanner.nextLine();
			studentDetail.setAddress(mobile);
			try{
			connection=ConnectionProvider.getConnection();
			String passData="update StudentData set mobile=? where id=?";
			preparedStatement=(PreparedStatement) connection.prepareStatement(passData);
			preparedStatement.setString(1, studentDetail.getMobile());
			preparedStatement.setString(2, studentDetail.getId());
			
			preparedStatement.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		break;
		case 4 :
		{
			String id,oldId;
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			System.out.println("Please enter student old id ");
			id=scanner.nextLine();
			studentDetail.setId(id);
			System.out.println("Please enter student new id ");
			oldId=scanner.nextLine();
			studentDetail.setOldId(oldId);
			try{
			connection=ConnectionProvider.getConnection();
			String passData="update StudentData set id=? where id=?";
			preparedStatement=(PreparedStatement) connection.prepareStatement(passData);
			preparedStatement.setString(1, studentDetail.getOldId());
			preparedStatement.setString(2, studentDetail.getId());
			
			preparedStatement.executeUpdate();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		break;
		default:
		System.out.println("wrong enter");
		break;
		}
		
	}
	@Override
	public void retrieveData() 
	{
		try 
		{
			connection=ConnectionProvider.getConnection();
			String query="select * from StudentData";
			statement=(Statement) connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);
			while(resultSet.next())
			{
				String name=resultSet.getString("name");
				String id=resultSet.getString("id");
				String address=resultSet.getString("address");
				String mobile=resultSet.getString("mobile");
				System.out.println("Name : "+name+" id : "+id+" address : "+address+" mobile : "+mobile);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	
}
