package com.kopo.lab0527;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class DB {

	Connection connection;
	private String dbFileName;
	private String dbTableName;
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DB(String databaseFileName, String dbTableName) {
		this.dbFileName = databaseFileName;
		this.dbTableName = dbTableName;
	}

	public boolean open() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean close() {
		if (this.connection == null) {
			return true;
		}
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int createTable() throws Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}

		String query = "CREATE TABLE " + this.dbTableName
				+ "(idx INT PRIMARY KEY, name TEXT, midScore INT, finScore INT);";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		return result;
	}

	public void insertData(String name, int midScore, int finScore) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.print("이름 입력 > ");
//		String name = scanner.next();
//
//		System.out.print("중간고사 성적 입력 > ");
//		int midScore = Integer.parseInt(scanner.next());
//
//		System.out.print("기말고사 성적 입력 > ");
//		int finScore = Integer.parseInt(scanner.next());
//
//		String query = "INSERT INTO " + this.dbTableName + "(name, midScore, finScore) VALUES('" + name + "'" + ","
//				+ midScore + "," + finScore + ");";
//		System.out.println(query);
//		Statement statement = this.connection.createStatement();
//		int result = statement.executeUpdate(query);
//		statement.close();
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		String query = "INSERT INTO scores (name, midScore, finScore) VALUES(?,?,?)";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, midScore);
			statement.setInt(3, finScore);
			int rowsAffected = statement.executeUpdate(); // INSERT, UPDATE, DELETE면 executeUpdate()
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public boolean selectData() throws SQLException {
		boolean result = false;
		String query = "SELECT * FROM " + this.dbTableName + " WHERE ?;";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println(resultSet);
		if (resultSet.next()) {
			System.out.println(resultSet.getString("name"));
			result = true;
		}
		resultSet.close();
		preparedStatement.close();
		return result;
	}

	public void selectAll() {
		this.open();
		try {
			String query = "SELECT * FROM scores";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				int midScore = result.getInt("midScore");
				int finScore = result.getInt("finScore");
				System.out.println(name + ", " + midScore + ", " + finScore);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
}
