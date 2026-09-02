package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Question;
import model.Score;
import model.User;

public class QuizService {
	private final QuestionService questionService;
	private final ScoreService scoreService;
	private final Scanner sc;

	public QuizService(QuestionService questionService, ScoreService scoreService, Scanner sc) {
		super();
		this.questionService = questionService;
		this.scoreService = scoreService;
		this.sc = sc;
	}

	public void startQuiz(User user) {
		List<Question> questions = getQuizQuestions();

		int correctAnswers = conductQuiz(questions);

		double percentage = calculatePercentage(correctAnswers, questions.size());

		saveScore(user, questions.size(), correctAnswers, percentage);

		displayResult(user.getUserName(), questions.size(), correctAnswers, percentage);
	}

	public List<Question> getQuizQuestions() {
		return new ArrayList<>(questionService.getAllQuestions());
	}

	public int conductQuiz(List<Question> questions) {
		System.out.println();
		System.out.println("----------QUIZ STARTS----------");
		int correctAnswers = 0;
		for (Question question : questions) {
			System.out.println();

			System.out.println(question.getQuestion());

			String[] options = question.getOptions();
			// display options
			for (int i = 0; i < options.length; i++) {
				System.out.println((i + 1) + ". " + options[i]);
			}

			System.out.print("\nEnter your answer[option number]: ");
			int userAnswer = sc.nextInt();

			if (userAnswer == question.getCorrectOption()) {
				correctAnswers++;
			}
			System.out.println();
		}
		System.out.println("----------QUIZ ENDS----------");
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

	public void displayResult(String userName, int totalQuestions, int correctAnswers, double percentage) {
		System.out.println();
		System.out.println("----------Score----------");
		System.out.println("User name      : " + userName);
		System.out.println("Total Questions: " + totalQuestions);
		System.out.println("Correct Answers: " + correctAnswers);
		System.out.println("Percentage     : " + percentage);
		System.out.println();
	}

}
