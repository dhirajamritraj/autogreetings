package com.autogreetings.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static Connection dbInstance;

	public static Connection createConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}

		System.out.println("MySQL JDBC Driver Registered!");

		try {
			dbInstance = DriverManager.getConnection("jdbc:mysql://10.127.127.185:3306/auto_greetings", "root", "root");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (dbInstance != null) {
			System.out.println("You made it, take control your database now!");
			return dbInstance;
		} else {
			System.out.println("Failed to make connection!");
			return null;

		}
	}

}
