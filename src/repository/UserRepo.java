package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.User;

/*
 * similar to QuestionRepo, for CRUD operations
 * currently not interested in adding update functionality for user
 * admin simply needs to delete the existing user credentials to be able to create a new account(i.e password, username changes)
 */
public class UserRepo {
	private final static String FILE_NAME = "users.dat";

	private List<User> users = new ArrayList<>();

	public UserRepo() {
		super();
		loadUsers();
	}

	public void saveUser(User user) {
		user.setId(generateNextId());
//		user.setRole(Role.USER);// changed to be done at MenuService
		users.add(user);
		saveToFile();
	}

	public void saveAdmin(User user) {
		user.setId(generateNextId());
//		user.setRole(Role.ADMIN);
		users.add(user);
		saveToFile();
	}

	public User findById(long id) {
		for (User u : users) {
			if (u.getId() == id)
				return u;
		}
		return null;
	}

	public User findByUserName(String userName) {
		for (User u : users) {
			if (u.getUserName() != null && u.getUserName().equals(userName))
				return u;
		}
		return null;
	}

	public List<User> getAllUsers() {
		return new ArrayList<>(users);
	}

	public boolean deleteUser(long id) {
		ListIterator<User> itr = users.listIterator();
		while (itr.hasNext()) {
			if (itr.next().getId() == id) {
				itr.remove();
				saveToFile();
				return true;
			}
		}

		return false;
	}

	private long generateNextId() {
		long maxId = 0;
		for (User u : users) {
			if (u.getId() > maxId) {
				maxId = u.getId();
			}
		}
		return maxId + 1;
	}

	@SuppressWarnings("unchecked")
	private void loadUsers() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			users = new ArrayList<>();
			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			users = (List<User>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			users = new ArrayList<>();
			System.out.println("Could not load users. Starting with empty list.");
		}
	}

	private void saveToFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(users);
		} catch (IOException e) {
			System.out.println("Error while saving user(s): " + e.getMessage());
		}

	}
}
