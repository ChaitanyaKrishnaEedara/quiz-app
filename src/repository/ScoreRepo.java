package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Score;

public class ScoreRepo {
	private final static String FILE_NAME = "scores.dat";

	private List<Score> scores = new ArrayList<>();

	public ScoreRepo() {
		super();
		loadScores();
	}

	public void saveScore(Score score) {
		scores.add(score);
		saveToFile();
	}

	public List<Score> getAllScores() {
		return new ArrayList<>(scores);
	}

	public Score findById(long id) {
		for (Score q : scores) {
			if (q.getId() == id)
				return q;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void loadScores() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			scores = new ArrayList<>();
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			scores = (List<Score>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			scores = new ArrayList<>();
			System.out.println("Could not load scores. Starting with empty list.");
		}
	}

	private void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(scores);
		} catch (IOException e) {
			System.out.println("Error while saving score(s): " + e.getMessage());
		}

	}
}
