package service;

import java.util.List;

import model.Score;
import repository.ScoreRepo;

public class ScoreService {
	private ScoreRepo scoreRepo = new ScoreRepo();

	public void saveScore(Score score) {
		scoreRepo.saveScore(score);
	}

	public List<Score> getAllScores() {
		return scoreRepo.getAllScores();
	}

	public Score findById(long id) {
		return scoreRepo.findById(id);
	}

}
