package com.kopo.lab0611;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		this.open();
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		String query = "CREATE TABLE IF NOT EXISTS " + this.dbTableName + " ("
				+ "idx INTEGER PRIMARY KEY AUTOINCREMENT, " + "id TEXT, " + "pwd TEXT, " + "userType TEXT, "
				+ "name TEXT, " + "phone TEXT, " + "address TEXT, " + "created TEXT, " + "lastUpdated TEXT" + ")";

		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();
		this.close();
		return result;
	}

	public void insertData(String id, String pwd, String name, String userType, String phone, String address,
			String created, String lastUpdated) throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}

		String sql = "INSERT INTO user " + "(id, pwd, name, userType, phone, address, created, lastUpdated) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			stmt.setString(3, name);
			stmt.setString(4, userType);
			stmt.setString(5, phone);
			stmt.setString(6, address);
			stmt.setString(7, created);
			stmt.setString(8, lastUpdated);

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Inserted rows: " + rowsAffected);

		} catch (SQLException e) {
			System.err.println("Error inserting data: " + e.getMessage());
			throw e;
		} finally {

			this.close();
		}
	}

	public boolean checkUser(String id, String pw) throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		try {
			String sql = "SELECT COUNT(1) FROM " + this.dbTableName + " WHERE id = ? AND pwd = ?";

			try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
				ps.setString(1, id);
				ps.setString(2, pw);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return rs.getInt(1) > 0;
					}
				}
			}
			return false;

		} finally {
			this.close();
		}
	}

	public String getUserType(String id) throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "SELECT userType FROM " + this.dbTableName + " WHERE id = ?";
		try (PreparedStatement ps = this.connection.prepareStatement(query)) {
			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getString("userType");
				}
			}
			return "error";
		} finally {
			this.close();
		}
	}

	public int insertScore(String name, String grade, String midScore, String finScore) throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "INSERT INTO " + this.dbTableName + " (name, grade, midScore, finScore) VALUES (?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, grade);
		pstmt.setString(3, midScore);
		pstmt.setString(4, finScore);
		int result = pstmt.executeUpdate();

		pstmt.close();
		this.close();
		return result;

	}

	// 중간고사 총합 구하는 쿼리 ( 학년 별 추가 구현 )
	public double getSumOfMidScore() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "SELECT SUM(midScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double sum = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			sum = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();
		this.close();
		return sum;
	}

	// 기말고사 총합 구하는 쿼리
	public double getSumOfFinScore() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "SELECT SUM(finScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double sum = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			sum = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();

		return sum;
	}

	// 중간고사 평균 구하는 쿼리
	public double getMeanOfMidScore() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "SELECT AVG(midScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double avg = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			avg = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();
		this.close();
		return avg;
	}

	// 기말고사 평균 구하는 쿼리
	public double getMeanOfFinScore() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		String query = "SELECT AVG(finScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double avg = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			avg = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();
		this.close();
		return avg;
	}

	// 순위 출력하는 쿼리
	public List<StudentRankDTO> getRankList() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}
		List<StudentRankDTO> result = new ArrayList<>();

		String query = "SELECT idx,name, midScore, finScore, "
				+ "RANK() OVER (ORDER BY (midScore + finScore) DESC) AS rank " + "FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			Double mid = rs.getDouble("midScore");
			Double fin = rs.getDouble("finScore");
			int rank = rs.getInt("rank");

			result.add(new StudentRankDTO(idx, name, mid, fin, rank));
		}

		rs.close();
		pstmt.close();
		this.close();
		return result;
	}

	public List<StudentRankDTO> showList() throws SQLException {
		this.open();
		if (this.connection == null) {
			throw new SQLException("DB connection failed to open");
		}

		List<StudentRankDTO> result = new ArrayList<>();
		String query = "SELECT idx, name, midScore, finScore FROM " + this.dbTableName;

		try (PreparedStatement pstmt = this.connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				Double mid = rs.getDouble("midScore");
				Double fin = rs.getDouble("finScore");
				result.add(new StudentRankDTO(idx, name, mid, fin));
			}

			return result;
		} finally {
			this.close();
		}
	}

	public List<StudentRankDTO> deleteScore(int idx, String name) throws SQLException {
		List<StudentRankDTO> stuList = new ArrayList<>();
		this.open();
		// 1. 삭제 쿼리
		String deleteQuery = "DELETE FROM " + this.dbTableName + " WHERE idx = ? AND name = ?";
		PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
		deleteStmt.setInt(1, idx);
		deleteStmt.setString(2, name);
		deleteStmt.executeUpdate();
		deleteStmt.close();

		// 2. 삭제 후 남은 데이터 SELECT
		String selectQuery = "SELECT idx, name, midScore, finScore, "
				+ "RANK() OVER (ORDER BY (midScore + finScore) DESC) AS rank FROM " + this.dbTableName;
		PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
		ResultSet rs = selectStmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("idx");
			String studentName = rs.getString("name");
			Double mid = rs.getDouble("midScore");
			Double fin = rs.getDouble("finScore");
			int rank = rs.getInt("rank");

			stuList.add(new StudentRankDTO(id, studentName, mid, fin, rank));
		}

		rs.close();
		selectStmt.close();
		this.close();

		return stuList;
	}

}
