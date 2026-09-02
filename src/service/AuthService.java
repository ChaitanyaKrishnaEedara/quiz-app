package service;

import java.util.List;

import model.User;

//for login and register
public class AuthService {
	private final UserService userService;

	public AuthService(UserService userService) {
		super();
		this.userService = userService;
	}

	public boolean login(String userName, String password) {
		List<User> users = userService.getAllUsers();
		for (User u : users) {
			if (!(u.getUserName().equals(null)) && u.getUserName().equals(userName)
					&& u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkRegistration(String userName) {
		List<User> users = userService.getAllUsers();
		for (User u : users) {
			if (!(u.getUserName().equals(null)) && u.getUserName().equals(userName)) {
				return true;// conveys that the userName exists already
			}
		}
		return false;
	}
}
