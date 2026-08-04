package service;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import model.Question;

public class MenuService {
	private static QuestionService questionService = new QuestionService();

	public void adminActions() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Select the action you want to perform");
		System.out.println("1.Add question\n2.Print all questions in order\nEnter your choice: ");
		int choice = Integer.parseInt(sc.nextLine());

		switch (choice) {
		case 1 -> {
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

			System.out.println("Question saved successfully.");
		}
		case 2 -> {
			List<Question> questions = questionService.getAllQuestions();
			ListIterator<Question> itr = questions.listIterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
				System.out.println();
			}
		}
		}
	}
}
