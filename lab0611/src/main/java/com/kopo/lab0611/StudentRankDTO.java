package com.kopo.lab0611;

public class StudentRankDTO {
	private int idx;
	private String name;
	private int grade;
	private double midScore;
	private double finScore;
	private int rank;

	public StudentRankDTO(int idx, String name, double midScore, double finScore, int rank) {
		this.idx = idx;
		this.name = name;
		this.midScore = midScore;
		this.finScore = finScore;
		this.rank = rank;
	}

	public StudentRankDTO(int idx, String name, double midScore, double finScore) {
		this.idx = idx;
		this.name = name;
		this.midScore = midScore;
		this.finScore = finScore;
		this.rank = 0; // 기본값
	}

	public String getName() {
		return name;
	}

	public int getGrade() {
		return grade;
	}

	public double getMidScore() {
		return midScore;
	}

	public double getFinScore() {
		return finScore;
	}

	public int getRank() {
		return rank;
	}

	public int getIdx() {
		return idx;
	}
}
