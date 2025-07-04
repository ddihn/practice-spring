package com.kopo.lab0610;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

public class DataReader {

	private Connection connection;
	private String dbFileName;
	private String dbTableName;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataReader(String databaseFileName, String dbTableName) {
		this.dbFileName = databaseFileName;
		this.dbTableName = dbTableName;
	}

	public boolean open() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName, config.toProperties());
			System.out.println("DB path = " + connection.getMetaData().getURL());
			// 한번 실행

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

	// 성적 테이블 생성
	public int createTable() throws Exception {
		if (this.connection == null) {
			throw new Exception("DB is not open");
		}
		// 이미 DB 있을 때는 있다고 하고 넘어가는 로직 추가
		String query = "CREATE TABLE IF NOT EXISTS " + this.dbTableName
				+ "(idx INTEGER PRIMARY KEY, name TEXT, grade REAL, midScore REAL, finScore REAL);";
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(query);
		statement.close();

		return result;
	}

	// 입력한 성적 DB insert
	public int insertData(String name, String grade, String midScore, String finScore) throws SQLException {
		String query = "INSERT INTO " + this.dbTableName + " (name, grade, midScore, finScore) VALUES (?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, grade);
		pstmt.setString(3, midScore);
		pstmt.setString(4, finScore);
		int result = pstmt.executeUpdate();

		pstmt.close();

		return result;

	}

	// 중간고사 총합 구하는 쿼리 ( 학년 별 추가 구현 )
	public double getSumOfMidScore() throws SQLException {
		String query = "SELECT SUM(midScore) FROM " + this.dbTableName;
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

	// 기말고사 총합 구하는 쿼리
	public double getSumOfFinScore() throws SQLException {
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
		String query = "SELECT AVG(midScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double avg = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			avg = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();

		return avg;
	}

	// 기말고사 평균 구하는 쿼리
	public double getMeanOfFinScore() throws SQLException {
		String query = "SELECT AVG(finScore) FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		double avg = 0;
		if (rs.next()) { // 결과 행 있는지 확인
			avg = rs.getDouble(1); // 첫 번째 컬럼값 읽기
		}
		rs.close();
		pstmt.close();

		return avg;
	}

	// 순위 출력하는 쿼리
	public List<StudentRankDTO> getRankList() throws SQLException {
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

		return result;
	}

	public List<StudentRankDTO> showList() throws SQLException {
		List<StudentRankDTO> result = new ArrayList<>();

		String query = "SELECT idx, name, midScore, finScore FROM " + this.dbTableName;
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			Double mid = rs.getDouble("midScore");
			Double fin = rs.getDouble("finScore");

			// rank 없는 생성자 사용
			result.add(new StudentRankDTO(idx, name, mid, fin));
		}

		rs.close();
		pstmt.close();

		return result;
	}

	public List<StudentRankDTO> deleteScore(int idx, String name) throws SQLException {
		List<StudentRankDTO> stuList = new ArrayList<>();

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

		return stuList;
	}
}
