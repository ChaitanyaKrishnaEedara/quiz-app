package service;

import java.util.List;

import model.Question;
import repository.QuestionRepo;

public class QuestionService {
	private QuestionRepo questionRepo = new QuestionRepo();

	public void saveQuestion(Question question) {
		questionRepo.saveQuestion(question);
	}

	public List<Question> getAllQuestions() {
		return questionRepo.getAllQuestions();
	}

	public Question findById(long id) {
		return questionRepo.findById(id);
	}

	public boolean updateQuestion(Question q) {
		return questionRepo.updateQuestion(q);
	}

	public boolean deleteQuestion(long id) {
		return questionRepo.deleteQuestion(id);
	}
}
