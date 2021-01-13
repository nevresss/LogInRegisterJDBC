package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImplementation implements UserDaoInterface {

	@Override
	public void createUser(String name, String lastName, String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = ConnectionManager.getInstance().getConnection();

		String query = "INSERT INTO users(name, lastName, username, password) " + "values(?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			statement.setString(2, lastName);
			statement.setString(3, username);
			statement.setString(4, password);

			statement.executeUpdate();
			System.out.println("User account successfully added! ");
		}

	}

	@Override
	public String loginUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub

		String str = "";
		Connection connection = ConnectionManager.getInstance().getConnection();

		String query = "SELECT * FROM users WHERE username = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				str = rs.getString("usersPassword");
				rs.close();
			} else {
				str = "Mistake!";
			}
		}
		return str;
	}

	@Override
	public void updateFirstName(String name, String username) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = ConnectionManager.getInstance().getConnection();

		String query = "UPDATE users SET name = ? WHERE username = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			statement.setString(2, username);

			statement.executeUpdate();
		}

		System.out.println("You have successfully changed your name! ");
	}

	@Override
	public void updateLastName(String lastName, String username) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = ConnectionManager.getInstance().getConnection();

		String query = "UPDATE users SET lastName = ? WHERE username = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, lastName);
			statement.setString(2, username);

			statement.executeUpdate();
		}

		System.out.println("You have successfully changed your lastname! ");
	}

	@Override
	public boolean checkingUserName(String username) throws SQLException {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM users";
		boolean validacija = false;
		
		Connection connection = ConnectionManager.getInstance().getConnection();

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			if (username.equals(rs.getString("username"))) {
				validacija = true;
			}
		}
		return validacija;
	}

	@Override
	public void getUserInfo(String username) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection connection = ConnectionManager.getInstance().getConnection();

		String query = "SELECT * FROM users WHERE username = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				System.out.println("Username: " + rs.getString("userName"));
				System.out.println("Name: " + rs.getString("firstName"));
				System.out.println("Lastname: " + rs.getString("lastName"));
			}
		}
	}
	/*
	@Override
	public void updatePassword(String username, String password) throws SQLException{
		
		String query = "UPDATE users SET password = ? WHERE username = ?";
		
		try (PreparedStatement statement = Connection.prepareStatement(query);) {
			
			statement.setString(1, password);
			statement.setString(2, username);
			
			statement.executeUpdate();
		}
	}
	*/

	/*@Override
	public void updatePassword(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		
	}
*/
}
