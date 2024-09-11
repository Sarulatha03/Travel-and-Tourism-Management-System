package service;

import dao.UserDAO;
import model.User;
import java.util.List;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // Method to register a new user
    public void registerUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    // Method to authenticate user login
    public User loginUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
}
