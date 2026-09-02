package service;

import java.util.List;

import model.User;
import repository.UserRepo;

public class UserService {
	private final UserRepo userRepo = new UserRepo();

	public void saveUser(User user) {
		userRepo.saveUser(user);
	}

	public void saveAdmin(User user) {
		userRepo.saveAdmin(user);
	}

	public List<User> getAllUsers() {
		return userRepo.getAllUsers();
	}

	public User findById(long id) {
		return userRepo.findById(id);
	}

	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	public boolean deleteUser(long id) {
		return userRepo.deleteUser(id);
	}
}
