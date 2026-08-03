package model;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String question;
	private String[] options;
	private String answer;

//	Topic topic;
//	QCategory category;
	public Question(long id, String question, String[] options, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.options = options;
		this.answer = answer;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

//	@Override
//	public String toString() {
//		return "Question [id=" + id + ", question=" + question + ", options=" + Arrays.toString(options) + ", answer="
//				+ answer + "]";
//	}
	
	@Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer='" + answer + '\'' +
                '}';
    }

}
