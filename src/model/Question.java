package model;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String question;
	private String[] options;
	private int correctOption;
	private Category category;

//	Topic topic;
//	QCategory category;
	public Question(long id, String question, String[] options, int correctOption, Category category) {
		super();
		this.id = id;
		this.question = question;
		this.options = options;
		this.correctOption = correctOption;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public int getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}

//	@Override
//	public String toString() {
//		return "Question [id=" + id + ", question=" + question + ", options=" + Arrays.toString(options) + ", answer="
//				+ answer + "]";
//	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Question{" + "id=" + id + ", question='" + question + '\'' + ", options=" + Arrays.toString(options)
				+ ", answer='" + correctOption + '\'' + ", category='" + category + '\'' + '}';
	}

}
