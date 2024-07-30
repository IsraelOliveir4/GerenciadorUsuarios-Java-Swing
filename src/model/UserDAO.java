package model;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (nome, telefone, email, cpf, senha_hash) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getTelefone());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getCpf());
            pstmt.setString(5, user.getSenhaHash());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // Código de erro para chave duplicada
                System.out.println("CPF já cadastrado.");
            }
            throw e;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET nome = ?, telefone = ?, email = ?, senha_hash = ? WHERE cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getTelefone());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getSenhaHash());
            pstmt.setString(5, user.getCpf());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(String cpf) throws SQLException {
        String sql = "DELETE FROM users WHERE cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha_hash")
                );
                user.setId(rs.getInt("id"));
                users.add(user);
            }
        }
        return users;
    }

    public List<User> searchUsers(String keyword) throws SQLException {
        String sql = "SELECT * FROM users WHERE nome LIKE ? OR email LIKE ? OR cpf LIKE ?";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cpf"),
                            rs.getString("senha_hash")
                    );
                    user.setId(rs.getInt("id"));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public User getUserByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cpf"),
                            rs.getString("senha_hash")
                    );
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }
        }
        return null;
    }
}
