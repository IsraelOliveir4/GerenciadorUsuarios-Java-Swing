package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UserView extends JFrame {
    private UserController userController;

    // Components
    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JTextField searchField;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserView() {
        userController = new UserController();

        // Setup the frame
        setTitle("User Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();

        // Form inputs
        nomeField = new JTextField();
        telefoneField = new JTextField();
        emailField = new JTextField();
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(telefoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(cpfField);
        formPanel.add(new JLabel("Senha:"));
        formPanel.add(senhaField);

        // Buttons
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton listButton = new JButton("Listar Todos");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        // Search
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");

        searchPanel.add(new JLabel("Buscar (Nome, Email ou CPF):"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Table
        userTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Telefone", "Email", "CPF"}, 0);
        userTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        // Add components to main panel
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(searchPanel, BorderLayout.SOUTH);
        panel.add(tableScrollPane, BorderLayout.EAST);

        // Add main panel to frame
        add(panel);

        // Add ActionListeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUsers();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUsers();
            }
        });
    }

    private void addUser() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());

        try {
            boolean added = userController.addUser(nome, telefone, email, cpf, senha);
            if (added) {
                JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso.");
                clearFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    private void updateUser() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());

        try {
            boolean updated = userController.updateUser(nome, telefone, email, cpf, senha);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void deleteUser() {
        String cpf = cpfField.getText();

        try {
            boolean deleted = userController.deleteUser(cpf);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir usuário: " + e.getMessage());
        }
    }

    private void listUsers() {
        try {
            List<User> users = userController.getAllUsers();
            updateTable(users);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar usuários: " + e.getMessage());
        }
    }

    private void searchUsers() {
        String keyword = searchField.getText();

        try {
            List<User> users = userController.searchUsers(keyword);
            updateTable(users);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuários: " + e.getMessage());
        }
    }

    private void updateTable(List<User> users) {
        tableModel.setRowCount(0); // Clear existing rows

        for (User user : users) {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getNome(),
                    user.getTelefone(),
                    user.getEmail(),
                    user.getCpf()
            });
        }
    }

    private void clearFields() {
        nomeField.setText("");
        telefoneField.setText("");
        emailField.setText("");
        cpfField.setText("");
        senhaField.setText("");
    }

}
