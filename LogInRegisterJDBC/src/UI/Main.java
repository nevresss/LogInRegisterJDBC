package UI;

import java.sql.SQLException;
import java.util.Scanner;
import DAO.UserDaoImplementation;

public class Main {

	static UserDaoImplementation userImplementation = new UserDaoImplementation();

	static Scanner input =  new Scanner (System.in); 
	public static void main(String[] args) throws SQLException{

		startMenu();
	}

	public static void startMenu() throws SQLException{

		System.out.println("Choose the option: ");
		System.out.println("1. SignUp");
		System.out.println("2. LogIN");
		
		int userAnswer = input.nextInt();
		
		switch (userAnswer) {
		case 1: 
			registrationUser();
		case 2:
			loggingUser();
				}
	}
	
	public static void registrationUser () throws SQLException{
		System.out.println("Enter first name: ");
		String firstName = input.next();
		
		System.out.println("Enter last name: ");
		String lastName = input.next();
		
		System.out.println("Enter username: ");
		String username = input.next();
		
		while(userImplementation.checkingUserName(username) == true) {
			System.out.println("Username is taken, try again: ");
			username = input.next();
		}
		
		System.out.println("Enter password: ");
		String password = input.next();
		
		while(userImplementation.checkingPassword(password) == false) {
			System.out.println("Password must have 6 characters. Try again: ");
			password = input.next();
		}
		
		userImplementation.createUser(firstName, lastName, username, password);
		System.out.println("You have successefuly signed up!");
		
		startMenu();
	}
	
	public static void loggingUser() throws SQLException{
		System.out.println("Enter username: ");
		String username = input.next();
		
		System.out.println("ENter password: ");
		String password = input.next();
		
		if(userImplementation.loginUser(username, password).equals("Mistake!")) {
			System.out.println("Wrong password.");
			startMenu();
		}else if(userImplementation.loginUser(username, password).equals(password)){
			System.out.println("You have successfully loged in!");
			operationMenu(username);
		}
		
	}
	public static void operationMenu(String username) throws SQLException{
		System.out.println("1 - Change firstname");
		System.out.println("2 - Change lastname");
		System.out.println("3 - Account info");
		System.out.println("4 - Log out");
		
		int userAnswer = input.nextInt();
		
		switch (userAnswer) {
		case 1:
			changingFirstName(username);
		case 2:
			changingLastName(username);
		case 3:
			userImplementation.getUserInfo(username);
		case 4:
			startMenu();
		}
	}
	
	public static void changingFirstName(String username) throws SQLException{
		System.out.println("Enter your new firstname: ");
		input.nextLine();
		String firstName = input.nextLine();
		
		userImplementation.updateFirstName(firstName, username);
	}
	
	public static void changingLastName(String username) throws SQLException{
		System.out.println("Enter your lastname: ");
		input.nextLine();
		String lastName = input.nextLine();
		
		userImplementation.updateLastName(lastName, username);
}

}













