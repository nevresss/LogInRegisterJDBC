package DAO;

import java.sql.SQLException;

public interface UserDaoInterface {

	
	public void createUser (String name, String lastName, String username, String password) throws SQLException;
	
	public String loginUser(String username, String password) throws SQLException;
	
	public void updateFirstName(String name, String username) throws SQLException;
	
	public void updateLastName(String lastName, String username) throws SQLException;
	
	public boolean checkingUserName(String username) throws SQLException;
	
	//public void updatePassword(String username, String password) throws SQLException;
	
	public void getUserInfo(String username) throws SQLException;

	//void updatePassword(String username, String password) throws SQLException;
	
}
