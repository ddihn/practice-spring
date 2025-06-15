package com.kopo.hw0611;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kopo.hw0611.util.HashUtil;


@Repository
public class DB {
	
	  private final DataSource dataSource;
	  private String dbTableName;
	  
	  @Autowired
	  public DB(DataSource dataSource) {
	      this.dataSource = dataSource;
	  }
	  
	  public void setDbTableName(String tableName) {
	      this.dbTableName = tableName;
	  }
	  
	  public boolean createUserTable() throws SQLException {
		    String query = "CREATE TABLE IF NOT EXISTS " + this.dbTableName + " ("
		                 + "idx INT AUTO_INCREMENT PRIMARY KEY, "
		                 + "id VARCHAR(50) NOT NULL, "
		                 + "pwd VARCHAR(255) NOT NULL, "
		                 + "userType VARCHAR(20), "
		                 + "name VARCHAR(50) NOT NULL, "
		                 + "phone VARCHAR(20), "
		                 + "address VARCHAR(255), "
		                 + "created DATETIME, "
		                 + "lastupdated DATETIME"
		                 + ") DEFAULT CHARSET=utf8mb4";

		    try (Connection conn = dataSource.getConnection()) {
		    	Statement stmt = conn.createStatement();
		        stmt.executeUpdate(query);
		        return true;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	  public boolean signup(String id,String pwd, String userType, String name, String phone,String address, String created, String lastupdated) throws SQLException {
		  String query = "INSERT INTO "+this.dbTableName+ "(id, pwd, userType, name, phone, address, created, lastupdated) VALUES(?,?,?,?,?,?,?,?)";
		  
		  try (Connection conn = dataSource.getConnection()){
			  String hashedPw = HashUtil.sha512(pwd);
			  
			  PreparedStatement pstmt = conn.prepareStatement(query); 
			  pstmt.setString(1, id);
			  pstmt.setString(2, hashedPw);
			  pstmt.setString(3, userType);
			  pstmt.setString(4, name);
			  pstmt.setString(5, phone);
			  pstmt.setString(6, address);
			  pstmt.setString(7, created);
			  pstmt.setString(8, lastupdated);
			  
			  int rowsAffected = pstmt.executeUpdate();
			  return rowsAffected > 0;
			  
		  } catch(SQLException e) {
			  e.printStackTrace();
			  return false;
		  }
	  }
	  
	  public User signin(String id, String pwd) throws SQLException {
		    String query = "SELECT idx, userType, name FROM " + this.dbTableName + " WHERE id = ? AND pwd = ?";
		    
		    try (Connection conn = dataSource.getConnection()) {
		        String hashedPw = HashUtil.sha512(pwd);  // 비밀번호 해싱
		        PreparedStatement pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, id);
		        pstmt.setString(2, hashedPw);

		        ResultSet rs = pstmt.executeQuery(); 
		        
		        if (rs.next()) {
		            User user = new User();
		            user.setIdx(rs.getString("idx"));
		            user.setName(rs.getString("name"));
		            user.setUserType(rs.getString("userType"));
		            return user;
		        } else {
		            return null;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e; 
		    }
		}

	  public List<User> showUserList() throws SQLException {
		    String query = "SELECT idx, id, name, userType, created FROM " + this.dbTableName;

		    List<User> users = new ArrayList<>();

		    try (Connection conn = dataSource.getConnection();
		         Statement st = conn.createStatement();
		         ResultSet rs = st.executeQuery(query)) {

		        while (rs.next()) {
		            User user = new User();
		            user.setIdx(rs.getString("idx"));
		            user.setId(rs.getString("id"));
		            user.setName(rs.getString("name"));
		            user.setUserType(rs.getString("userType"));
		            user.setCreated(rs.getString("created"));
		            users.add(user);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }

		    return users;
		}

	  public User findUserById(String id) throws SQLException {
		    String query = "SELECT id, name, userType, phone, address, created, lastupdated FROM " + this.dbTableName + " WHERE id = ?";
		    try (Connection conn = dataSource.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {

		        pstmt.setString(1, id);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            User user = new User();
		            user.setId(rs.getString("id"));
		            user.setName(rs.getString("name"));
		            user.setUserType(rs.getString("userType"));
		            user.setPhone(rs.getString("phone"));
		            user.setAddress(rs.getString("address"));
		            return user;
		        } else {
		            return null;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		}

	  public boolean deleteUser(int idx) throws SQLException {
		    String query = "DELETE FROM " + this.dbTableName + " WHERE idx = ?";
		    try (Connection conn = dataSource.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setInt(1, idx);
		        int affected = pstmt.executeUpdate();
		        return affected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		}
	  
	  public User getUserByIdx(String idx) throws SQLException {
		    String query = "SELECT idx, id, name, userType, phone, address, created, lastupdated FROM " + this.dbTableName + " WHERE idx = ?";

		    try (Connection conn = dataSource.getConnection()) {
		        PreparedStatement pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, idx);

		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            User user = new User();
		            user.setIdx(rs.getString("idx"));
		            user.setId(rs.getString("id"));
		            user.setName(rs.getString("name"));
		            user.setUserType(rs.getString("userType"));
		            user.setPhone(rs.getString("phone"));
		            user.setAddress(rs.getString("address"));
		            user.setCreated(rs.getString("created"));
		            return user;
		        } else {
		            return null;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		}
	  
	  public boolean updateUserByIdx(String idx, String phone, String address, String pwd) throws SQLException {
		    String query;
		    boolean updatePassword = (pwd != null && !pwd.trim().isEmpty());

		    if (updatePassword) {
		        query = "UPDATE " + this.dbTableName + " SET phone = ?, address = ?, pwd = ? WHERE idx = ?";
		    } else {
		        query = "UPDATE " + this.dbTableName + " SET phone = ?, address = ? WHERE idx = ?";
		    }

		    try (Connection conn = dataSource.getConnection()) {
		        PreparedStatement pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, phone);
		        pstmt.setString(2, address);

		        if (updatePassword) {
		            String hashedPw = HashUtil.sha512(pwd);
		            pstmt.setString(3, hashedPw);
		            pstmt.setString(4, idx);
		        } else {
		            pstmt.setString(3, idx);
		        }

		        int rowsAffected = pstmt.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		}
	  
	// 전체 회원 수 반환
	  public int countTotalUsers() throws SQLException {
	      String query = "SELECT COUNT(*) AS count FROM " + this.dbTableName;
	      try (Connection conn = dataSource.getConnection();
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery(query)) {
	          if (rs.next()) {
	              return rs.getInt("count");
	          }
	      }
	      return 0;
	  }

	  // 오늘 가입한 회원 수 반환
	  public int countTodayUsers() throws SQLException {
	      String query = "SELECT COUNT(*) AS count FROM " + this.dbTableName + " WHERE DATE(created) = CURDATE()";
	      try (Connection conn = dataSource.getConnection();
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery(query)) {
	          if (rs.next()) {
	              return rs.getInt("count");
	          }
	      }
	      return 0;
	  }

}
