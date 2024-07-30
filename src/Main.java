import util.DatabaseConnection;
import view.UserView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Falha na conexão com o banco de dados: " + e.getMessage());
        }

        // Inicializa a interface gráfica em um thread separado para melhor desempenho e evitar problemas de sincronização.
        SwingUtilities.invokeLater(() -> {
            UserView userView = new UserView();
            userView.setVisible(true);
        });
    }
}
