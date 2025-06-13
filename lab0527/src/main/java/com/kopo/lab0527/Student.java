package com.kopo.lab0527;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Student {
	int idx;
	String name;
	int midScore;
	int finScore;
	DB db = new DB("c:\\kopo\\tomcat.sqlite", "scores");

	Student() {

	}

	Student(String name, int midScore, int finScore) {
		this.name = name;
		this.midScore = midScore;
		this.finScore = finScore;
	}

	public ArrayList<Student> selectAll() {
		db.open();
		ArrayList<Student> data = new ArrayList<Student>();
		try {
			String query = "SELECT * FROM student";
			PreparedStatement statement = db.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				int midScore = result.getInt("midScore");
				int finScore = result.getInt("finScore");
				data.add(new Student(name, midScore, finScore));
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.close();
		return data;
	}
}
