import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private final Map<String, User> users = new HashMap<>();
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        super("Online Examination - Login");
        seedUsers();
        setupUI();
    }

    private void seedUsers() {
        users.put("shirish", new User("shirish", "pass123", "Shirish"));
        users.put("student", new User("student", "pass123", "Student User"));
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        Utils.applyPurpleTheme();

        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(Utils.PURPLE_START);

        JLabel title = Utils.titleLabel("Online Examination System");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        root.add(title, BorderLayout.NORTH);

        JPanel formPanel = Utils.sectionPanel();
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Username"), gbc);
        gbc.gridx = 1;
        usernameField = Utils.roundedTextField(30);
        form.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Password"), gbc);
        gbc.gridx = 1;
        passwordField = Utils.roundedPasswordField(30);
        form.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        JButton clearBtn = Utils.purpleButton("Clear");
        clearBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
        form.add(clearBtn, gbc);

        gbc.gridx = 1;
        JButton loginBtn = Utils.purpleButton("Login");
        loginBtn.addActionListener(e -> performLogin());
        form.add(loginBtn, gbc);

        formPanel.add(form, BorderLayout.CENTER);
        root.add(formPanel, BorderLayout.CENTER);

        setContentPane(root);
    }

    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (!users.containsKey(username)) {
            Utils.showMessage(this, "User not found", "Login Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        User u = users.get(username);
        if (!u.getPassword().equals(password)) {
            Utils.showMessage(this, "Invalid password", "Login Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DashboardFrame df = new DashboardFrame(u, this::logoutAndReturnToLogin);
        df.setVisible(true);
        setVisible(false);
    }

    private void logoutAndReturnToLogin() {
        usernameField.setText("");
        passwordField.setText("");
        setVisible(true);
    }
}