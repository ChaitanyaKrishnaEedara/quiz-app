package app;

import java.util.Scanner;

import model.Role;
import model.User;
import service.MenuService;

public class Main {
	private MenuService menuService = new MenuService();
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		User user;
		Main mainObj = new Main();
//		UserService userService = new UserService();
//		userService.saveAdmin(new User(0, "admin1", "Admin@04", Role.ADMIN));
		user = mainObj.login();
//		add exception handling
		try {
			if (user.getRole() == Role.ADMIN) {
				mainObj.menuService.adminActions();
			} else if (user.getRole() == Role.USER) {
				mainObj.menuService.userActions();
			}
		} catch (NullPointerException e) {
			System.err.println("///// ///// /////");
		}

	}

	public User login() {
		/*
		 * MY IDEAS - method is for admin and user login or first time registration
		 * logic(might implement business logic in service layer) usually only an admin
		 * can add another admin and the first admin is added manually users can create
		 * a new unique account with username and password or login to an existing
		 * account
		 */
		User user = null;
		System.out.println("Are you an existing user? (yes/no)");
		String existing = sc.nextLine();
		if (existing.equalsIgnoreCase("no") || existing.equalsIgnoreCase("n")) {
			menuService.register();
			login();
		} else if (existing.equalsIgnoreCase("yes") || existing.equalsIgnoreCase("y")) {
			user = menuService.login();
		} else {
			System.out.println("**********Stopping the session**********");
		}
		return user;
	}
}
