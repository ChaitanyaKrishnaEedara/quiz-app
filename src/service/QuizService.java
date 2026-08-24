package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Question;
import model.Score;
import model.User;

public class QuizService {
	private QuestionService questionService = new QuestionService();
	private ScoreService scoreService = new ScoreService();
	private final Scanner sc = new Scanner(System.in);

	public void startQuiz(User user) {
		List<Question> questions = getQuizQuestions();

		int correctAnswers = conductQuiz(questions);

		double percentage = calculatePercentage(correctAnswers, questions.size());

		saveScore(user, questions.size(), correctAnswers, percentage);
//
//        displayResult(...);
	}

	public List<Question> getQuizQuestions() {
		return new ArrayList<>(questionService.getAllQuestions());
	}

	public int conductQuiz(List<Question> questions) {
		int correctAnswers = 0;
		for (Question question : questions) {

			System.out.println(question.getQuestion());

			String[] options = question.getOptions();
			// display options
			for (int i = 0; i < options.length; i++) {
				System.out.println((i + 1) + ". " + options[i]);
			}

			System.out.println("Enter your answer: [option number]");
			int userAnswer = sc.nextInt();

			if (userAnswer == question.getCorrectOption()) {
				correctAnswers++;
			}
		}
		return correctAnswers;
	}

	public double calculatePercentage(int correctAnswers, int totalQuestions) {
		double percentage = ((double) correctAnswers / totalQuestions) * 100;
		return percentage;
	}

	public void saveScore(User user, int totalQuestions, int correctAnswers, double percentage) {
		Score score = new Score(0, user.getId(), totalQuestions, correctAnswers, percentage, LocalDateTime.now());
		scoreService.saveScore(score);
	}

}
