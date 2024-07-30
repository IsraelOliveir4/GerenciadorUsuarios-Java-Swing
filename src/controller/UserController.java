package controller;

import model.User;
import model.UserDAO;
import util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean addUser(String nome, String telefone, String email, String cpf, String senha) throws SQLException {
        String senhaHash = PasswordUtil.hashPassword(senha);
        User user = new User(nome, telefone, email, cpf, senhaHash);
        return userDAO.addUser(user);
    }

    public boolean updateUser(String nome, String telefone, String email, String cpf, String senha) throws SQLException {
        User existingUser = userDAO.getUserByCpf(cpf);
        if (existingUser != null) {
            String senhaHash = existingUser.getSenhaHash();
            if (!senha.isEmpty()) {
                senhaHash = PasswordUtil.hashPassword(senha);
            }
            User user = new User(nome, telefone, email, cpf, senhaHash);
            return userDAO.updateUser(user);
        }
        return false;
    }

    public boolean deleteUser(String cpf) throws SQLException {
        return userDAO.deleteUser(cpf);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public List<User> searchUsers(String keyword) throws SQLException {
        return userDAO.searchUsers(keyword);
    }
}
