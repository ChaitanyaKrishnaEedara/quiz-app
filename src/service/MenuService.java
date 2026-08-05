package service;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import model.Question;
import model.Role;
import model.User;

public class MenuService {
	private QuestionService questionService = new QuestionService();
	private AuthService authService = new AuthService();
	private UserService userService = new UserService();
	Scanner sc = new Scanner(System.in);

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
				System.out.println("User with same username exists. Try registering with a different username.\n");
			} else {
				System.out.println("Registration successful.\n");
			}
		} while (userExists);
		User u = new User(0, userName, password, Role.USER);
		userService.saveUser(u);
	}

	public void adminActions() {

		String decision;
		do {
			System.out.println("Select the action you want to perform");
			System.out.println("1. Add a question\n" + "2. Print all questions in order\n"
					+ "3. Update a question by its ID\n" + "4. Delete a question by its ID\n"
					+ "Enter any other number to stop the session\n" + "Enter your choice: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1 -> {
				System.out.println("----------ADD QUESTION----------");
				System.out.print("Enter question id: ");
				long id = Long.parseLong(sc.nextLine());

				System.out.print("Enter the question: ");
				String que = sc.nextLine();

				String[] options = new String[4];
				System.out.println("Enter the 4 options:");
				for (int i = 0; i < 4; i++) {
					options[i] = sc.nextLine();
				}

				System.out.print("Enter the answer: ");
				String answer = sc.nextLine();

				Question question = new Question(id, que, options, answer);
				questionService.saveQuestion(question);

				System.out.println("Question saved successfully.\n");
			}
			case 2 -> {
				System.out.println("----------ALL QUESTIONS----------");
				List<Question> questions = questionService.getAllQuestions();
				ListIterator<Question> itr = questions.listIterator();
				while (itr.hasNext()) {
					System.out.println(itr.next());
					System.out.println();
				}
			}
			case 3 -> {
				System.out.println("----------UPDATE----------");
				System.out.print("Enter the id of desired question: ");
				long id = Long.parseLong(sc.nextLine());

				System.out.print("Enter the new question or paste the same question: ");
				String que = sc.nextLine();

				String[] options = new String[4];
				System.out.println("Enter new 4 options or paste old options:");
				for (int i = 0; i < 4; i++) {
					options[i] = sc.nextLine();
				}

				System.out.print("Enter the answer: ");
				String answer = sc.nextLine();

				Question question = new Question(id, que, options, answer);
				boolean status = questionService.updateQuestion(question);
				if (status) {
					System.out.println("Question updated successfully.\n");
				} else {
					System.out.println("An error occured while updating the question! Try again\n");
				}
			}
			case 4 -> {
				System.out.println("----------DELETE----------");
				System.out.println("Enter the ID of desired question: ");
				long id = Long.parseLong(sc.nextLine());

				boolean status = questionService.deleteQuestion(id);

				if (status) {
					System.out.println("Question deleted successfully.\n");
				} else {
					System.out.println("An error occured while deleting the question!\n");
				}
			}
			}

			System.out.println("\nDo you wish to continue: (yes/no)");
			decision = sc.nextLine();
		} while (decision.equalsIgnoreCase("yes") || decision.equalsIgnoreCase("y"));
		System.out.println("**********Stopping the session**********");
	}

	public void userActions() {
		System.out.println("This is placeholder code\n");
	}
}
