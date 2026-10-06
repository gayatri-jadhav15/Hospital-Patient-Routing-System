package com.kiranAcademy.Hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

	private static Connection con = null;

	public static Connection getConnection() {

		try {

			if (con == null || con.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver");

				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/hospital_db",
						"root",
						"gayatriJadhav@15");
			}

		} catch (ClassNotFoundException e) {

			System.out.println("Driver Not Found");
			e.printStackTrace();

		} catch (SQLException e) {

			System.out.println("Database Connection Failed");
			e.printStackTrace();
		}

		return con;
	}
	
	public static void main(String[] args) {
		Connection con = DB_Connection.getConnection();

		if (con != null) {
			System.out.println("Database Connected Successfully");
		} else {
			System.out.println("Connection Failed");
		}
	}

	}

