package service;

import java.util.List;

import model.User;
import repository.UserRepo;

//for login and register
public class AuthService {
	private final UserRepo userRepo = new UserRepo();

	public boolean login(String userName, String password) {
		List<User> users = userRepo.getAllUsers();
		for (User u : users) {
			if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkRegistration(String userName) {
		List<User> users = userRepo.getAllUsers();
		for (User u : users) {
			if (u.getUserName().equals(userName)) {
				return true;// conveys that the userName exists already
			}
		}
		return false;
	}
}
