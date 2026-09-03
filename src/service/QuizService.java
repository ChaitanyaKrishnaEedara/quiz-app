package service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Category;
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

		Category category = questions.get(0).getCategory();

		Collections.shuffle(questions);

		int correctAnswers = conductQuiz(questions);

		double percentage = calculatePercentage(correctAnswers, questions.size());

		saveScore(user, category, questions.size(), correctAnswers, percentage);

		displayResult(user.getUserName(), category, questions.size(), correctAnswers, percentage);
	}

	public List<Question> getQuizQuestions() {
		List<Question> allQuestions = questionService.getAllQuestions();

		int input;
		Category category = null;
		do {
			System.out.println("Choose the category to which you want to start the quiz...");
			System.out.println("1. Java\n2. MySQL\n3. HTML\n4. CSS\n5. JavaScript\n");
			input = sc.nextInt();
			sc.nextLine();
			switch (input) {
			case 1 -> {
				category = Category.JAVA;
			}
			case 2 -> {
				category = Category.MYSQL;
			}
			case 3 -> {
				category = Category.HTML;
			}
			case 4 -> {
				category = Category.CSS;
			}
			case 5 -> {
				category = Category.JAVASCRIPT;
			}
			default -> {
				System.out.println("Invalid category\n");
			}
			}
		} while (input < 1 || input > 5);

		final Category selectedCategory = category;

		List<Question> categorizedQuestions = allQuestions.stream().filter(q -> q.getCategory() == selectedCategory)
				.collect(Collectors.toList());

		return categorizedQuestions;
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
			sc.nextLine();

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

	public void saveScore(User user, Category category, int totalQuestions, int correctAnswers, double percentage) {
		Score score = new Score(0, user.getId(), category, totalQuestions, correctAnswers, percentage,
				LocalDateTime.now());
		scoreService.saveScore(score);
	}

	public void displayResult(String userName, Category category, int totalQuestions, int correctAnswers,
			double percentage) {
		System.out.println();
		System.out.println("----------Score----------");
		System.out.println("User name      : " + userName);
		System.out.println("Category       : " + category);
		System.out.println("Total Questions: " + totalQuestions);
		System.out.println("Correct Answers: " + correctAnswers);
		System.out.println("Percentage     : " + percentage);
		System.out.println();
	}

}
