package service;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import model.Category;
import model.Question;
import model.Role;
import model.Score;
import model.User;

public class MenuService {
	private final QuestionService questionService = new QuestionService();
	private final UserService userService = new UserService();
	private final ScoreService scoreService = new ScoreService();

	private final Scanner sc = new Scanner(System.in);

	private final AuthService authService = new AuthService(userService);
	private final QuizService quizService = new QuizService(questionService, scoreService, sc);

	private User user;

	public void startSession() {
		while (true) {
			System.out.println("Are you an existing user? (yes/no)");
			String existing = sc.nextLine();
			if (existing.equalsIgnoreCase("no") || existing.equalsIgnoreCase("n")) {
				register();
			} else if (existing.equalsIgnoreCase("yes") || existing.equalsIgnoreCase("y")) {
				user = login();
				break;
			} else {
				System.out.println("**********Stopping the session**********");
				return;
			}
		}

		if (user.getRole() == Role.ADMIN) {
			adminActions();
		} else if (user.getRole() == Role.USER) {
			userActions();
		}
	}

	public User login() {
		String userName;
		String password;
		boolean matchFound;
		do {
			System.out.println();
			System.out.println("*******LOGIN*******");
			System.out.println("Enter your username: ");
			userName = sc.nextLine();
			System.out.println("Enter your password: ");
			password = sc.nextLine();

			matchFound = authService.login(userName, password);
			if (matchFound) {
				System.out.println("Login successful\n");
//				break;
			} else {
				System.out.println("Invalid credentials!! Try again\n");
			}
		} while (!matchFound);
		return userService.findByUserName(userName);
	}

	public void register() {
		String userName;
		String password;
		boolean userExists;
		do {
			System.out.println();
			System.out.println("*******USER REGISTRATION*******");
			System.out.println("Enter your username: ");
			userName = sc.nextLine();
			System.out.println("Enter your password: ");
			password = sc.nextLine();
			userExists = authService.checkRegistration(userName);
			if (userExists) {
				System.out.println("User with same username exists or You've entered an invalid username."
						+ " Try registering with a different username.\n");
			} else {
				System.out.println("Registration successful.\n");
			}
		} while (userExists);
		User u = new User(0, userName, password, Role.USER);
		userService.saveUser(u);
	}

	public void registerAdmin() {
		String userName;
		String password;
		boolean userExists;
		do {
			System.out.println();
			System.out.println("Enter username: ");
			userName = sc.nextLine();
			System.out.println("Enter password: ");
			password = sc.nextLine();
			userExists = authService.checkRegistration(userName);
			if (userExists) {
				System.out.println("Admin/User with same username already exists or You've entered an invalid username."
						+ " Try registering with a different username.\n");
			} else {
				System.out.println("Admin registration successful.\n");
			}
		} while (userExists);
		User u = new User(0, userName, password, Role.ADMIN);
		userService.saveUser(u);
	}

	public void adminActions() {

		String decision;
		do {
			System.out.println("Select the action you want to perform");
			System.out.println("1. Add a question\n" + "2. Print all questions in order\n"
					+ "3. Update a question by its ID\n" + "4. Delete a question by its ID\n" + "5. View all users\n"
					+ "6. View user by ID\n" + "7. View all User scores\n" + "8. View score by User\n"
					+ "9. Add an Admin\n" + "10. Delete an User\n" + "Enter any other number to stop the session\n"
					+ "\nEnter your choice: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1 -> {
				System.out.println("----------ADD A QUESTION----------");
				System.out.print("Enter the question: ");
				String que = sc.nextLine();

				String[] options = new String[4];
				System.out.println("Enter the 4 options:");
				for (int i = 0; i < 4; i++) {
					options[i] = sc.nextLine();
				}

				System.out.print("Enter the correct option: ");
				int correctOption = sc.nextInt();
				sc.nextLine();

				int input;
				Category category = null;
				do {
					System.out.println("Choose the category for the question");
					System.out.println("1. Java\n2. MySQL\n3. HTML\n4. CSS\n5. JavaScript");
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
				} while (input < 1 && input > 5);

				Question question = new Question(0, que, options, correctOption, category);
				questionService.saveQuestion(question);

				System.out.println("Question saved successfully.\n");
				System.out.println("------------------------------");
			}
			case 2 -> {
				System.out.println("----------ALL QUESTIONS----------");
				List<Question> questions = questionService.getAllQuestions();
				ListIterator<Question> itr = questions.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
				System.out.println("------------------------------");
			}
			case 3 -> {
				System.out.println("----------UPDATE A QUESTION----------");
				System.out.print("Enter the id of desired question: ");
				long id = Long.parseLong(sc.nextLine());
				Question originalQuestion = questionService.findById(id);

				System.out.print("Enter the new question or enter blank to assign previous question: ");
				String que = sc.nextLine();
				if (que.equals("")) {
					que = originalQuestion.getQuestion();
				}

				String[] options = new String[4];
				System.out.println("Enter new 4 options or enter blank to assign previous options:");
				for (int i = 0; i < 4; i++) {
					options[i] = sc.nextLine();
					if (options[i].equals("")) {
						options[i] = originalQuestion.getOptions()[i];
					}
				}

				System.out.println("Enter the correct option or enter 0 to assign previous correct option: ");
				int correctOption = sc.nextInt();
				sc.nextLine();
				if (correctOption == 0) {
					correctOption = originalQuestion.getCorrectOption();
				}

				int input;
				Category category = null;
				do {
					System.out.println("Choose the category for the question");
					System.out.println("1. Java\n2. MySQL\n3. HTML\n4. CSS\n5. JavaScript");
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

				Question question = new Question(id, que, options, correctOption, category);
				boolean status = questionService.updateQuestion(question);
				if (status) {
					System.out.println("Question updated successfully.\n");
				} else {
					System.out.println("An error occured while updating the question! Try again\n");
				}
				System.out.println("------------------------------");
			}
			case 4 -> {
				System.out.println("----------DELETE A QUESTION----------");
				System.out.println("Enter the ID of desired question: ");
				long id = Long.parseLong(sc.nextLine());

				boolean status = questionService.deleteQuestion(id);

				if (status) {
					System.out.println("Question deleted successfully.\n");
				} else {
					System.out.println("An error occured while deleting the question!\n");
				}
				System.out.println("------------------------------");
			}
			case 5 -> {
				System.out.println("----------ALL USERS LIST----------");
				List<User> users = userService.getAllUsers();
				ListIterator<User> itr = users.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
			}
			case 6 -> {
				System.out.println("----------USER DETAILS----------");
				System.out.println("Enter the id of User: ");
				long id = Long.parseLong(sc.nextLine());

				User user = userService.findById(id);
				if (user == null) {
					System.out.println("The User with the specified id does not exist!");
				} else {
					System.out.println(user);
				}

			}
			case 7 -> {
				System.out.println("----------ALL USERS SCORES----------");
				List<Score> scores = scoreService.getAllScores();
				ListIterator<Score> itr = scores.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
				System.out.println("------------------------------");
			}
			case 8 -> {
				System.out.println("----------VIEW A USER SCORES----------");
				System.out.println("Enter the id of desired user: ");
				long id = Long.parseLong(sc.nextLine());
				List<Score> userScores = scoreService.getScoresByUserId(id);
				ListIterator<Score> itr = userScores.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
				System.out.println("------------------------------");
			}
			case 9 -> {
				System.out.println("----------ADD AN ADMIN----------");
				registerAdmin();
				System.out.println("------------------------------");
			}
			case 10 -> {
				System.out.println("----------DELETE AN USER----------");
				System.out.println("Enter the id of desired User: ");
				long id = Long.parseLong(sc.nextLine());

				boolean status = userService.deleteUser(id);

				if (status) {
					System.out.println("User deleted successfully\n");
				} else {
					System.out.println("An error occured while deleting user! Try again!\n");
				}
				System.out.println("------------------------------");
			}
			}

			System.out.println("\nDo you wish to continue: (yes/no)");
			decision = sc.nextLine();
		} while (decision.equalsIgnoreCase("yes") || decision.equalsIgnoreCase("y"));
		System.out.println("**********Stopping the session**********");
	}

	public void userActions() {
		String decision;
		do {
			System.out.println("Select the action you want to perform");
			System.out.println("1. Start the quiz\n" + "2. View your previous scores\n"
					+ "Enter any other number to stop the session\n" + "\nEnter your choice: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1 -> {
				quizService.startQuiz(user);
			}
			case 2 -> {
				System.out.println("----------YOUR SCORES----------");
				List<Score> userScores = scoreService.getScoresByUserId(user.getId());
				ListIterator<Score> itr = userScores.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
				System.out.println("------------------------------");
			}
			}
			System.out.println("\nDo you wish to continue: (yes/no)");
			decision = sc.nextLine();
		} while (decision.equalsIgnoreCase("yes") || decision.equalsIgnoreCase("y"));
		System.out.println("**********Stopping the session**********");

	}
}
