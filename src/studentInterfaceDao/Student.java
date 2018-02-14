package studentInterfaceDao;

import java.sql.SQLException;

public interface Student 
{
	void tableCreate() throws SQLException;
	void insert();
	void delete();
	void update();
	void retrieveData();
}
