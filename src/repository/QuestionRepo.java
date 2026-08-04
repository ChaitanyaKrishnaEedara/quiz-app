package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionRepo {
	private final static String FILE_NAME = "questions.dat";

	private List<Question> questions = new ArrayList<>();

	public QuestionRepo() {
		super();
		loadQuestions();
	}

	public void saveQuestion(Question question) {
		questions.add(question);
		saveToFile();
	}

	public List<Question> getAllQuestions() {
		return new ArrayList<>(questions);
	}

	public Question findById(long id) {
		for (Question q : questions) {
			if (q.getId() == id)
				return q;
		}
		return null;
	}

	public boolean updateQuestion(Question updatedQuestion) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getId() == updatedQuestion.getId()) {
				questions.set(i, updatedQuestion);
				saveToFile();
				return true;
			}
		}

		return false;
	}

	public boolean deleteQuestion(long id) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getId() == id) {
				questions.remove(i);
				saveToFile();
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private void loadQuestions() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			questions = new ArrayList<>();
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			questions = (List<Question>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			questions = new ArrayList<>();
			System.out.println("Could not load questions. Starting with empty list.");
		}
	}

	private void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(questions);
		} catch (IOException e) {
			System.out.println("Error while saving questions: " + e.getMessage());
		}

	}
}
