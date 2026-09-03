package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Score implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long userId;// linked with User service's id when retrieving data(by filtering) using
						// streams
	private Category category;
	private int totalQuestions;
	private int correctAnswers; // correctly answered questions since we can't skip a question for them to be
								// unanswered
//	private int score; // most likely percentage
	private double percentage;
	private LocalDateTime dateTime;

	public Score(long id, long userId, Category category, int totalQuestions, int correctAnswers, double percentage,
			LocalDateTime dateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.category = category;
		this.totalQuestions = totalQuestions;
		this.correctAnswers = correctAnswers;
		this.percentage = percentage;
		this.dateTime = dateTime;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

//	public double getScore() {
//		return score;
//	}
//
//	public void setScore(int score) {
//		this.score = score;
//	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", userId=" + userId + ", category=" + category + ", totalQuestions="
				+ totalQuestions + ", correctAnswers=" + correctAnswers + ", percentage=" + percentage + ", dateTime="
				+ dateTime + "]";
	}

}
