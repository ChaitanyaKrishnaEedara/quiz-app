package model;

import java.time.LocalDate;

public class Score {
	private long id;
	private long userId;
	private int totalQuestionsAnswered;
	private double score;
	private LocalDate date;

	public Score(long id, long userId, int totalQuestionsAnswered, double score, LocalDate date) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalQuestionsAnswered = totalQuestionsAnswered;
		this.score = score;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getTotalQuestionsAnswered() {
		return totalQuestionsAnswered;
	}

	public void setTotalQuestionsAnswered(int totalQuestionsAnswered) {
		this.totalQuestionsAnswered = totalQuestionsAnswered;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", userId=" + userId + ", totalQuestionsAnswered=" + totalQuestionsAnswered
				+ ", score=" + score + ", date=" + date + "]";
	}

}
